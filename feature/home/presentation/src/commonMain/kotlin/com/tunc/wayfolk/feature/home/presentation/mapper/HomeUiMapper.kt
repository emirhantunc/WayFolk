package com.tunc.wayfolk.feature.home.presentation.mapper

import com.tunc.wayfolk.feature.home.domain.model.HomePost
import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import com.tunc.wayfolk.feature.home.presentation.model.HomePostUiModel
import com.tunc.wayfolk.feature.home.presentation.model.HomeStoryUiModel
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.text.UiText
import com.tunc.wayfolk.uicomponent.avatar.WayFolkAvatarRing
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds

internal class HomeUiMapper(
    private val clock: Clock,
) {

    fun toUiModel(story: HomeStory): HomeStoryUiModel = HomeStoryUiModel(
        id = story.id,
        username = if (story.isOwnStory) UiText.Resource(WayFolkStrings.Home.yourStory) else UiText.Raw(story.username),
        imageUrl = story.imageUrl,
        ring = when {
            story.isOwnStory || story.isSeen -> WayFolkAvatarRing.Inactive
            else -> WayFolkAvatarRing.Active
        },
    )

    fun toUiModel(post: HomePost): HomePostUiModel = HomePostUiModel(
        id = post.id,
        authorId = post.author.id,
        authorName = post.author.displayName,
        authorUsername = post.author.username,
        authorAvatarUrl = post.author.avatarUrl,
        authorRing = if (post.author.hasActiveStory) WayFolkAvatarRing.Active else WayFolkAvatarRing.None,
        location = post.location,
        imageUrl = post.imageUrl,
        caption = post.caption,
        likeCount = post.likeCount,
        commentCount = post.commentCount,
        isLiked = post.isLiked,
        isSaved = post.isSaved,
        timeAgo = relativeTime(post.createdAtEpochMillis),
    )

    private fun relativeTime(epochMillis: Long): UiText {
        val elapsed = (clock.now().toEpochMilliseconds() - epochMillis).coerceAtLeast(0L).milliseconds
        val minutes = elapsed.inWholeMinutes.toInt()
        val hours = elapsed.inWholeHours.toInt()
        val days = elapsed.inWholeDays.toInt()
        return when {
            minutes < 1 -> UiText.Resource(WayFolkStrings.Home.timeJustNow)
            hours < 1 -> UiText.Plural(WayFolkStrings.Home.timeMinutesAgo, minutes, minutes)
            days < 1 -> UiText.Plural(WayFolkStrings.Home.timeHoursAgo, hours, hours)
            else -> UiText.Plural(WayFolkStrings.Home.timeDaysAgo, days, days)
        }
    }
}
