package com.tunc.wayfolk.feature.home.domain.model

data class HomeFeed(
    val stories: List<HomeStory>,
    val posts: List<HomePost>,
    val hasUnreadMessages: Boolean,
    val nextCursor: String?,
)

data class HomeStory(
    val id: String,
    val username: String,
    val imageUrl: String,
    val isOwnStory: Boolean,
    val isSeen: Boolean,
)

data class HomePost(
    val id: String,
    val author: HomePostAuthor,
    val location: String?,
    val imageUrl: String,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val createdAtEpochMillis: Long,
)

data class HomePostAuthor(
    val id: String,
    val displayName: String,
    val username: String,
    val avatarUrl: String,
    val hasActiveStory: Boolean,
)
