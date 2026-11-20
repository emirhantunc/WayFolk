package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.presentation.CoreViewModel
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import com.tunc.wayfolk.feature.home.presentation.fake.HomeFakeData
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class HomeViewModel(
    private val homeRepository: HomeRepository,
) : CoreViewModel() {

    private val _state = MutableStateFlow(HomeScreenContract.State(stories = HomeFakeData.stories))
    val state: StateFlow<HomeScreenContract.State> = _state.asStateFlow()

    init {
        loadFeed()
    }

    fun onAction(action: HomeScreenContract.Action) {
        when (action) {
            HomeScreenContract.Action.OnRetry -> loadFeed()
            is HomeScreenContract.Action.OnStoryImageStateChange -> updateStoryImageState(
                storyId = action.storyId,
                imageState = action.imageState,
            )
        }
    }

    private fun updateStoryImageState(storyId: String, imageState: StoryImageState) {
        _state.update { state ->
            if (state.storyImageStates[storyId] == imageState) {
                state
            } else {
                state.copy(storyImageStates = state.storyImageStates.put(storyId, imageState))
            }
        }
    }

    private fun loadFeed() {
        safeFlowApiCall { homeRepository.getFeed() }
            .onEach { result ->
                if (result is RestResult.Success) {
                    _state.update { it.copy(stories = result.result.stories.toImmutableList()) }
                }
            }
            .launchIn(viewModelScope)
    }
}
