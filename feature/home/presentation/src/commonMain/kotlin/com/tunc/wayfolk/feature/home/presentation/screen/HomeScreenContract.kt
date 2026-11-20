package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.runtime.Immutable
import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

internal object HomeScreenContract {

    @Immutable
    data class State(
        val stories: ImmutableList<HomeStory> = persistentListOf(),
        val storyImageStates: PersistentMap<String, StoryImageState> = persistentMapOf(),
    )

    sealed interface Action {
        data object OnRetry : Action

        data class OnStoryImageStateChange(
            val storyId: String,
            val imageState: StoryImageState,
        ) : Action
    }
}
