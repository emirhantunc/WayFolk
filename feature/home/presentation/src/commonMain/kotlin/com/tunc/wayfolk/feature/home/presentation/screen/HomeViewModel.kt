package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.presentation.CoreViewModel
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class HomeViewModel(
    private val homeRepository: HomeRepository,
) : CoreViewModel() {

    private val _state = MutableStateFlow(HomeScreenContract.State())
    val state: StateFlow<HomeScreenContract.State> = _state.asStateFlow()

    init {
        loadFeed()
    }

    fun onAction(action: HomeScreenContract.Action) {
        when (action) {
            HomeScreenContract.Action.OnRetry -> loadFeed()
        }
    }

    private fun loadFeed() {
        safeFlowApiCall { homeRepository.getFeed() }
            .onEach { result ->
                if (result is RestResult.Success) {
                    _state.update { it.copy(stories = result.result.stories) }
                }
            }
            .launchIn(viewModelScope)
    }
}
