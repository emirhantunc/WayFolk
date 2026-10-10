package com.tunc.wayfolk.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tunc.wayfolk.contract.model.CoreToastDuration
import com.tunc.wayfolk.contract.model.CoreToastModel
import com.tunc.wayfolk.contract.model.CoreToastType
import com.tunc.wayfolk.contract.model.LoadingModel
import com.tunc.wayfolk.contract.network.INetworkError
import com.tunc.wayfolk.contract.network.IResponseStatus
import com.tunc.wayfolk.contract.network.LoadingType
import com.tunc.wayfolk.contract.network.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class ViewModelBehaviourDelegate : ViewModelBehaviour {

    private var scope: CoroutineScope? = null

    private val networkState = MutableSharedFlow<NetworkState>()
    override val networkStateFlow = networkState.asSharedFlow()

    private val toastState = MutableSharedFlow<CoreToastModel>()
    override val toastStateFlow = toastState.asSharedFlow()

    private val loadingState = MutableSharedFlow<LoadingModel>()
    override val loadingStateFlow = loadingState.asSharedFlow()

    override var isLoading: Boolean by mutableStateOf(false)

    override fun setScope(viewModelScope: CoroutineScope) {
        scope = viewModelScope
    }

    override fun clearScope() {
        scope = null
    }

    override fun onServiceError(error: INetworkError?) {
        scope?.launch {
            networkState.emit(NetworkState.Error(error))
        }
    }

    override suspend fun onServiceErrorFlow(error: INetworkError?) {
        networkState.emit(NetworkState.Error(error))
    }

    override fun handleLoading(result: RestResult.Loading, loadingType: LoadingType) {
        isLoading = result.loading

        if (loadingType == LoadingType.None) return

        scope?.launch {
            loadingState.emit(
                LoadingModel(
                    show = result.loading,
                    loadingType = loadingType
                )
            )
        }
    }

    override fun showToast(
        message: String,
        description: String?,
        primaryButtonText: String?,
        secondaryButtonText: String?,
        showCloseButton: Boolean,
        onPrimaryButtonClick: (() -> Unit)?,
        onSecondaryButtonClick: (() -> Unit)?,
        type: CoreToastType,
        duration: CoreToastDuration
    ) {
        scope?.launch {
            toastState.emit(
                CoreToastModel(
                    message = message,
                    description = description,
                    primaryButtonText = primaryButtonText,
                    secondaryButtonText = secondaryButtonText,
                    onPrimaryButtonClick = onPrimaryButtonClick,
                    onSecondaryButtonClick = onSecondaryButtonClick,
                    showCloseButton = showCloseButton,
                    type = type,
                    duration = duration
                )
            )
        }
    }


    override suspend fun handleSuccessResult(result: Any, status: IResponseStatus?) {
        networkState.emit(NetworkState.Success(result, status))
    }
}