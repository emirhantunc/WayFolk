package com.tunc.wayfolk.feature.home.domain.model

data class HomeFeed(
    val stories: List<HomeStory>,
)

data class HomeStory(
    val id: String,
    val title: String,
    val imageUrl: String,
)
