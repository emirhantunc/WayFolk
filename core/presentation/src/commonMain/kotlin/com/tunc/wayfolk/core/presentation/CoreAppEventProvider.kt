package com.tunc.wayfolk.core.presentation

import com.tunc.wayfolk.contract.model.CoreToastModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


class AppEventProvider {
    private val _showToastFlow = Channel<CoreToastModel>(Channel.BUFFERED)
    val showToastFlow = _showToastFlow.receiveAsFlow()

    private val _showLoadingFlow = Channel<Boolean>()
    val showLoadingFlow = _showLoadingFlow.receiveAsFlow()

    private val _accessibilityFlow = Channel<String>(Channel.BUFFERED)
    val accessibilityFlow = _accessibilityFlow.receiveAsFlow()

    private var loadingCounter = 0

    fun showToast(toastModel: CoreToastModel) {
        _showToastFlow.trySend(toastModel)
    }

    fun updateLoadingState(show: Boolean) {
        if (show) {
            loadingCounter++
            _showLoadingFlow.trySend(true)
        } else {
            loadingCounter--

            if (loadingCounter <= 0) {
                _showLoadingFlow.trySend(false)
            }
        }
    }

    fun announceForAccessibility(message: String) {
        if (message.isNotEmpty()) {
            _accessibilityFlow.trySend(message)
        }
    }
}