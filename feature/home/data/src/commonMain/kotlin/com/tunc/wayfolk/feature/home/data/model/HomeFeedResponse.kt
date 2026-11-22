package com.tunc.wayfolk.feature.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HomeFeedResponse(
    @SerialName("stories") val stories: List<HomeStoryResponse>? = null,
    @SerialName("posts") val posts: List<HomePostResponse>? = null,
    @SerialName("hasUnreadMessages") val hasUnreadMessages: Boolean? = null,
    @SerialName("nextCursor") val nextCursor: String? = null,
)

@Serializable
internal data class HomeStoryResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("isOwnStory") val isOwnStory: Boolean? = null,
    @SerialName("isSeen") val isSeen: Boolean? = null,
)

@Serializable
internal data class HomePostResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("author") val author: HomePostAuthorResponse? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("imageUrl") val imageUrl: String? = null,
    @SerialName("caption") val caption: String? = null,
    @SerialName("likeCount") val likeCount: Int? = null,
    @SerialName("commentCount") val commentCount: Int? = null,
    @SerialName("isLiked") val isLiked: Boolean? = null,
    @SerialName("isSaved") val isSaved: Boolean? = null,
    @SerialName("createdAt") val createdAtEpochMillis: Long? = null,
)

@Serializable
internal data class HomePostAuthorResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("avatarUrl") val avatarUrl: String? = null,
    @SerialName("hasActiveStory") val hasActiveStory: Boolean? = null,
)
