package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.core.presentation.CoreViewModel
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import com.tunc.wayfolk.feature.home.presentation.mapper.HomeUiMapper
import com.tunc.wayfolk.feature.home.presentation.model.HomePostUiModel
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
    private val homeUiMapper: HomeUiMapper,
) : CoreViewModel() {

    private val _state = MutableStateFlow(HomeScreenContract.State())
    val state: StateFlow<HomeScreenContract.State> = _state.asStateFlow()

    init {
        loadFeed()
    }

    fun onAction(action: HomeScreenContract.Action) {
        when (action) {
            HomeScreenContract.Action.OnRetry -> loadFeed()
            is HomeScreenContract.Action.OnStoryImageStateChange -> updateStoryImageState(action.storyId, action.imageState)
            is HomeScreenContract.Action.OnPostLikeClick -> updatePost(action.postId) { post ->
                post.copy(
                    isLiked = !post.isLiked,
                    likeCount = (post.likeCount + if (post.isLiked) -1 else 1).coerceAtLeast(0),
                )
            }
            is HomeScreenContract.Action.OnPostSaveClick -> updatePost(action.postId) { post ->
                post.copy(isSaved = !post.isSaved)
            }
            HomeScreenContract.Action.OnNotificationsClick,
            HomeScreenContract.Action.OnMessagesClick,
            is HomeScreenContract.Action.OnStoryClick,
            is HomeScreenContract.Action.OnPostAuthorClick,
            is HomeScreenContract.Action.OnPostMoreClick,
            is HomeScreenContract.Action.OnPostCommentClick,
            is HomeScreenContract.Action.OnPostShareClick -> Unit
        }
    }

    private fun loadFeed() {
        safeFlowApiCall { homeRepository.getFeed() }
            .onEach { result ->
                if (result is RestResult.Success) onFeedLoaded(result.result)
            }
            .launchIn(viewModelScope)
    }

    private fun onFeedLoaded(feed: HomeFeed) {
        _state.update { state ->
            state.copy(
                stories = feed.stories.map(homeUiMapper::toUiModel).toImmutableList(),
                posts = feed.posts.map(homeUiMapper::toUiModel).toImmutableList(),
                hasUnreadMessages = feed.hasUnreadMessages,
            )
        }
    }

    private fun updatePost(postId: String, transform: (HomePostUiModel) -> HomePostUiModel) {
        _state.update { state ->
            state.copy(
                posts = state.posts
                    .map { post -> if (post.id == postId) transform(post) else post }
                    .toImmutableList(),
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
}
