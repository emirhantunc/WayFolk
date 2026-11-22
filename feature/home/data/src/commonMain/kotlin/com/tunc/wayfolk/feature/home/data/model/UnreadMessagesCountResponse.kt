package com.tunc.wayfolk.feature.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UnreadMessagesCountResponse(
    @SerialName("count") val count: Int? = null,
)
