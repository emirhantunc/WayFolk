package com.tunc.wayfolk.feature.home.data.mapper

import com.tunc.wayfolk.feature.home.data.remote.dto.HomeFeedResponse
import com.tunc.wayfolk.feature.home.data.remote.dto.HomeStoryResponse
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.model.HomeStory

internal fun HomeFeedResponse.toDomain(): HomeFeed = HomeFeed(
    stories = stories.orEmpty().mapNotNull { it.toDomain() },
)

private fun HomeStoryResponse.toDomain(): HomeStory? {
    val storyId = id ?: return null
    return HomeStory(
        id = storyId,
        title = title.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
    )
}
