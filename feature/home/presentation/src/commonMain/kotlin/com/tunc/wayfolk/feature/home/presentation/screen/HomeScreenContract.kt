package com.tunc.wayfolk.feature.home.presentation.screen

import com.tunc.wayfolk.feature.home.domain.model.HomeStory

internal object HomeScreenContract {

    data class State(
        val stories: List<HomeStory> = emptyList(),
    )

    sealed interface Action {
        data object OnRetry : Action
    }
}
