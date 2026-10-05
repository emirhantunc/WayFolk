package com.tunc.wayfolk.contract.network




sealed interface NetworkState {

    data class Error(val error: INetworkError?) : NetworkState

    data class Success(val args: Any, val status: IResponseStatus? = null) : NetworkState
}

