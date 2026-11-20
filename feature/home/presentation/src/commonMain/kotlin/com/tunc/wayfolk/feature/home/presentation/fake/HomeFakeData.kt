package com.tunc.wayfolk.feature.home.presentation.fake

import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

internal object HomeFakeData {

    private const val STORY_COUNT = 10
    private const val STORY_IMAGE_SIZE = 200

    val stories: ImmutableList<HomeStory> = List(STORY_COUNT) { index ->
        val number = index + 1
        HomeStory(
            id = "fake_story_$number",
            title = "Story $number",
            imageUrl = "https://picsum.photos/seed/wayfolk$number/$STORY_IMAGE_SIZE",
        )
    }.toImmutableList()
}
