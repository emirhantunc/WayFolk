package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.runtime.Immutable
import com.tunc.wayfolk.core.presentation.pagination.PaginationState
import com.tunc.wayfolk.feature.home.presentation.model.HomePostUiModel
import com.tunc.wayfolk.feature.home.presentation.model.HomeStoryUiModel
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

internal object HomeScreenContract {

    @Immutable
    data class State(
        val stories: ImmutableList<HomeStoryUiModel> = persistentListOf(),
        val posts: ImmutableList<HomePostUiModel> = persistentListOf(),
        val hasUnreadMessages: Boolean = false,
        val storyImageStates: PersistentMap<String, StoryImageState> = persistentMapOf(),
        val pagination: PaginationState = PaginationState(),
    )

    sealed interface Action {
        data object OnRetry : Action
        data object OnLoadMore : Action
        data object OnNotificationsClick : Action
        data object OnMessagesClick : Action
        data class OnStoryClick(val storyId: String) : Action
        data class OnStoryImageStateChange(val storyId: String, val imageState: StoryImageState) : Action
        data class OnPostAuthorClick(val authorId: String) : Action
        data class OnPostMoreClick(val postId: String) : Action
        data class OnPostLikeClick(val postId: String) : Action
        data class OnPostCommentClick(val postId: String) : Action
        data class OnPostShareClick(val postId: String) : Action
        data class OnPostSaveClick(val postId: String) : Action
    }
}
