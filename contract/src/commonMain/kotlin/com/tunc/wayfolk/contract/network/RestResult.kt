package com.tunc.wayfolk.contract.network

sealed class RestResult<out T> {
    data object Empty : RestResult<Nothing>()

    data class Error(val error: INetworkError) : RestResult<Nothing>()

    data class Loading(val loading: Boolean) : RestResult<Nothing>()

    data class Success<T>(val result: T) : RestResult<T>()

    data class SuccessWithStatus<T>(
        val result: T,
        val status: IResponseStatus?
    ) : RestResult<T>()
}
