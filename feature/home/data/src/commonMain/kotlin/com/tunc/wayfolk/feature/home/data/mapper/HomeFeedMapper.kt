package com.tunc.wayfolk.feature.home.data.mapper

import com.tunc.wayfolk.feature.home.data.remote.dto.HomeFeedResponse
import com.tunc.wayfolk.feature.home.data.remote.dto.HomePostAuthorResponse
import com.tunc.wayfolk.feature.home.data.remote.dto.HomePostResponse
import com.tunc.wayfolk.feature.home.data.remote.dto.HomeStoryResponse
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.model.HomePost
import com.tunc.wayfolk.feature.home.domain.model.HomePostAuthor
import com.tunc.wayfolk.feature.home.domain.model.HomeStory

internal fun HomeFeedResponse.toDomain(): HomeFeed = HomeFeed(
    stories = stories.orEmpty().mapNotNull { it.toDomain() },
    posts = posts.orEmpty().mapNotNull { it.toDomain() },
    hasUnreadMessages = hasUnreadMessages ?: false,
    nextCursor = nextCursor?.takeIf { it.isNotBlank() },
)

private fun HomeStoryResponse.toDomain(): HomeStory? {
    val storyId = id ?: return null
    val storyImageUrl = imageUrl ?: return null
    return HomeStory(
        id = storyId,
        username = username.orEmpty(),
        imageUrl = storyImageUrl,
        isOwnStory = isOwnStory ?: false,
        isSeen = isSeen ?: false,
    )
}

private fun HomePostResponse.toDomain(): HomePost? {
    val postId = id ?: return null
    val postAuthor = author?.toDomain() ?: return null
    val postImageUrl = imageUrl ?: return null
    return HomePost(
        id = postId,
        author = postAuthor,
        location = location?.takeIf { it.isNotBlank() },
        imageUrl = postImageUrl,
        caption = caption.orEmpty(),
        likeCount = likeCount ?: 0,
        commentCount = commentCount ?: 0,
        isLiked = isLiked ?: false,
        isSaved = isSaved ?: false,
        createdAtEpochMillis = createdAtEpochMillis ?: 0L,
    )
}

private fun HomePostAuthorResponse.toDomain(): HomePostAuthor? {
    val authorId = id ?: return null
    return HomePostAuthor(
        id = authorId,
        displayName = displayName.orEmpty(),
        username = username.orEmpty(),
        avatarUrl = avatarUrl.orEmpty(),
        hasActiveStory = hasActiveStory ?: false,
    )
}
