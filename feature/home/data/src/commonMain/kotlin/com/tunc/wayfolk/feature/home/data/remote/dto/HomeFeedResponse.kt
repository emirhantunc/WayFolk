package com.tunc.wayfolk.feature.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HomeFeedResponse(
    @SerialName("stories") val stories: List<HomeStoryResponse>? = null,
)

@Serializable
internal data class HomeStoryResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("imageUrl") val imageUrl: String? = null,
)
