package com.tunc.wayfolk.core.presentation

import androidx.annotation.VisibleForTesting
import com.tunc.wayfolk.contract.model.CoreToastDuration
import com.tunc.wayfolk.contract.model.CoreToastModel
import com.tunc.wayfolk.contract.model.CoreToastType
import com.tunc.wayfolk.contract.model.LoadingModel
import com.tunc.wayfolk.contract.network.ErrorType
import com.tunc.wayfolk.contract.network.INetworkError
import com.tunc.wayfolk.contract.network.IResponseStatus
import com.tunc.wayfolk.contract.network.LoadingType
import com.tunc.wayfolk.contract.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import kotlinx.serialization.json.JsonElement

@Suppress("ComplexInterface")
interface ViewModelBehaviour {

    val networkStateFlow: SharedFlow<NetworkState>

    val toastStateFlow: SharedFlow<CoreToastModel>

    val loadingStateFlow: SharedFlow<LoadingModel>

    var isLoading: Boolean

    fun setScope(viewModelScope: CoroutineScope)
    fun clearScope()

    fun startLoading(loadingType: LoadingType) {
        handleLoading(
            result = RestResult.Loading(true),
            loadingType = loadingType
        )
    }

    fun stopLoading(loadingType: LoadingType) {
        handleLoading(
            result = RestResult.Loading(false),
            loadingType = loadingType
        )
    }

    fun onServiceError(error: INetworkError?)

    suspend fun onServiceErrorFlow(error: INetworkError?)

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun <T : Any> safeFlowApiCall(
        loadingType: LoadingType = LoadingType.Content,
        errorType: ErrorType = ErrorType.Content,
        call: () -> Flow<RestResult<T>>
    ): Flow<RestResult<T>> {
        return try {
            call.invoke().transform { restResult ->
                handleResult(restResult, errorType, loadingType)
                emit(restResult)
            }
        } catch (exception: Exception) {
            onCatch(
                errorType = errorType,
                exception = exception
            )
        }.catch {
            onCatch<T>(
                errorType = errorType,
                exception = it
            )
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    suspend fun <T : Any> suspendSafeFlowApiCall(
        loadingType: LoadingType = LoadingType.Content,
        errorType: ErrorType = ErrorType.Content,
        call: suspend () -> Flow<RestResult<T>>
    ): Flow<RestResult<T>> {
        return try {
            call.invoke().transform { restResult ->
                handleResult(restResult, errorType, loadingType)
                emit(restResult)
            }
        } catch (exception: Exception) {
            onCatch(
                errorType = errorType,
                exception = exception
            )
        }.catch {
            onCatch<T>(
                errorType = errorType,
                exception = it
            )
        }
    }

    suspend fun <T : Any> handleResult(
        restResult: RestResult<T>,
        errorType: ErrorType,
        loadingType: LoadingType
    ) {
        when (restResult) {
            is RestResult.Empty -> {
                Unit
            }

            is RestResult.Error -> {
                if (errorType != ErrorType.None) {
                    onServiceErrorFlow(restResult.error)
                }
            }

            is RestResult.Loading -> {
                handleLoading(
                    result = restResult,
                    loadingType = loadingType
                )
            }

            is RestResult.Success -> {
                handleSuccessResult(restResult.result)
            }

            is RestResult.SuccessWithStatus -> {
                handleSuccessResult(restResult.result, restResult.status)
            }
        }
    }

    fun <T> onCatch(errorType: ErrorType, exception: Throwable) = flow<RestResult<T>> {
        val error = object : INetworkError {
            override var message: String? = exception.message
            override var data: JsonElement? = null
            override var code: Int? = null
            override var externalCode: String? = null
        }

        if (errorType != ErrorType.None) {
            onServiceError(error)
            val errorResult = RestResult.Error(error)
            emit(errorResult)
            return@flow
        }
    }

    fun handleLoading(result: RestResult.Loading, loadingType: LoadingType)

    fun showToast(
        message: String,
        description: String? = null,
        primaryButtonText: String? = null,
        secondaryButtonText: String? = null,
        showCloseButton: Boolean = false,
        onPrimaryButtonClick: (() -> Unit)? = null,
        onSecondaryButtonClick: (() -> Unit)? = null,
        type: CoreToastType,
        duration: CoreToastDuration = CoreToastDuration.Short
    )

    suspend fun handleSuccessResult(result: Any, status: IResponseStatus? = null)
}
