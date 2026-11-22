package com.tunc.wayfolk.feature.home.presentation.model

import androidx.compose.runtime.Immutable
import com.tunc.wayfolk.multilanguage.text.UiText
import com.tunc.wayfolk.uicomponent.avatar.WayFolkAvatarRing

@Immutable
data class HomeStoryUiModel(
    val id: String,
    val username: UiText,
    val imageUrl: String,
    val ring: WayFolkAvatarRing,
)

@Immutable
data class HomePostUiModel(
    val id: String,
    val authorId: String,
    val authorName: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val authorRing: WayFolkAvatarRing,
    val location: String?,
    val imageUrl: String,
    val caption: String,
    val likeCount: Int,
    val commentCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val timeAgo: UiText,
)
