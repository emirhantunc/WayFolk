package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.contract.network.ErrorType
import com.tunc.wayfolk.contract.network.LoadingType
import com.tunc.wayfolk.core.presentation.CoreViewModel
import com.tunc.wayfolk.core.presentation.pagination.PaginationBehaviour
import com.tunc.wayfolk.core.presentation.pagination.PaginationBehaviourDelegate
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.usecase.GetHomeFeedUseCase
import com.tunc.wayfolk.feature.home.domain.usecase.GetUnreadMessagesCountUseCase
import com.tunc.wayfolk.feature.home.presentation.mapper.HomeUiMapper
import com.tunc.wayfolk.feature.home.presentation.model.HomePostUiModel
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class HomeViewModel(
    private val getHomeFeedUseCase: GetHomeFeedUseCase,
    private val getUnreadMessagesCountUseCase: GetUnreadMessagesCountUseCase,
    private val homeUiMapper: HomeUiMapper,
) : CoreViewModel(),
    PaginationBehaviour<String, HomeFeed> by PaginationBehaviourDelegate() {

    private val _state = MutableStateFlow(HomeScreenContract.State())
    val state: StateFlow<HomeScreenContract.State> = _state.asStateFlow()

    init {
        setupPagination(
            scope = viewModelScope,
            request = { cursor, isFirstPage ->
                safeFlowApiCall(
                    loadingType = if (isFirstPage) LoadingType.Content else LoadingType.None,
                    errorType = if (isFirstPage) ErrorType.Content else ErrorType.None,
                ) {
                    getHomeFeedUseCase(cursor = cursor, pageSize = HomeConstants.Feed.PAGE_SIZE)
                }
            },
            nextKey = HomeFeed::nextCursor,
            onPageLoaded = ::onFeedPageLoaded,
            onStateChange = { pagination ->
                _state.update { state -> state.copy(pagination = pagination) }
            },
        )
        loadNextPage()
        loadUnreadMessagesCount()
    }

    fun onAction(action: HomeScreenContract.Action) {
        when (action) {
            HomeScreenContract.Action.OnRetry -> refreshPagination()
            HomeScreenContract.Action.OnLoadMore -> loadNextPage()
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

    private fun loadUnreadMessagesCount() {
        safeFlowApiCall(

            loadingType = LoadingType.None,
            errorType = ErrorType.None,
        ) {
            getUnreadMessagesCountUseCase()
        }
            .onEach { result ->
                if (result is RestResult.Success) {
                    _state.update { state -> state.copy(unreadMessagesCount = result.result) }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onFeedPageLoaded(feed: HomeFeed, isFirstPage: Boolean) {
        val newPosts = feed.posts.map(homeUiMapper::toUiModel)
        _state.update { state ->
            if (isFirstPage) {
                state.copy(
                    stories = feed.stories.map(homeUiMapper::toUiModel).toImmutableList(),
                    posts = newPosts.distinctBy { post -> post.id }.toImmutableList(),
                )
            } else {
                state.copy(
                    posts = (state.posts + newPosts).distinctBy { post -> post.id }.toImmutableList(),
                )
            }
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
