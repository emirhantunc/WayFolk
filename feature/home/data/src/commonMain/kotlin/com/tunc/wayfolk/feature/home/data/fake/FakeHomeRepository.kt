package com.tunc.wayfolk.feature.home.data.fake

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.model.HomePost
import com.tunc.wayfolk.feature.home.domain.model.HomePostAuthor
import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeHomeRepository(
    private val clock: Clock,
) : HomeRepository {

    private val authors = listOf(
        author(id = "sofia", displayName = "Sofia Andersen", username = "sofia.wanders"),
        author(id = "marco", displayName = "Marco Reyes", username = "marco.roams"),
        author(id = "leila", displayName = "Leila Mansour", username = "leila.m"),
        author(id = "tomasz", displayName = "Tomasz Wiśniewski", username = "tomasz_w"),
        author(id = "ana", displayName = "Ana Costa", username = "ana.travel"),
        author(id = "rei", displayName = "Rei Tanaka", username = "rei_jp"),
    )

    private val places = listOf(
        "Lofoten Islands, Norway" to "Woke up at 4am for this. Zero regrets. The silence here is something else. 🏔️",
        "Jiufen, Taiwan" to "Three weeks in Asia and still can't get enough of these mountain villages.",
        "Chefchaouen, Morocco" to "Every corner of this town is a different shade of blue.",
        "Kraków, Poland" to "Old town mornings before the crowds arrive.",
        "Lisbon, Portugal" to "Tram 28, pastel de nata, repeat.",
        "Kyoto, Japan" to "Found a quiet temple garden away from everything.",
    )

    override fun getFeed(cursor: String?, pageSize: Int): Flow<RestResult<HomeFeed>> = flow {
        emit(RestResult.Loading(true))
        delay(NETWORK_DELAY)

        val offset = cursor?.toIntOrNull() ?: 0
        val end = (offset + pageSize).coerceAtMost(TOTAL_POSTS)
        val isFirstPage = cursor == null

        emit(
            RestResult.Success(
                HomeFeed(
                    stories = if (isFirstPage) createStories() else emptyList(),
                    posts = (offset until end).map(::createPost),
                    hasUnreadMessages = true,
                    nextCursor = end.takeIf { it < TOTAL_POSTS }?.toString(),
                )
            )
        )
        emit(RestResult.Loading(false))
    }

    private fun createStories(): List<HomeStory> = listOf(
        story(id = "me", username = "me", isOwnStory = true, isSeen = true),
        story(id = "leila", username = "leila.m"),
        story(id = "tomasz", username = "tomasz_w"),
        story(id = "ana", username = "ana.travel"),
        story(id = "rei", username = "rei_jp"),
        story(id = "sofia", username = "sofia.wanders"),
        story(id = "marco", username = "marco.roams", isSeen = true),
    )

    private fun createPost(index: Int): HomePost {
        val author = authors[index % authors.size]
        val (location, caption) = places[index % places.size]
        return HomePost(
            id = "post_$index",
            author = author,
            location = location,
            imageUrl = image(seed = "post_$index", width = POST_WIDTH, height = POST_HEIGHT),
            caption = caption,
            likeCount = LIKE_BASE + index * LIKE_STEP,
            commentCount = COMMENT_BASE + index,
            isLiked = index % 3 == 0,
            isSaved = false,
            createdAtEpochMillis = (clock.now() - (index + 1).hours).toEpochMilliseconds(),
        )
    }

    private fun story(
        id: String,
        username: String,
        isOwnStory: Boolean = false,
        isSeen: Boolean = false,
    ) = HomeStory(
        id = "story_$id",
        username = username,
        imageUrl = image(seed = "story_$id", width = AVATAR_SIZE, height = AVATAR_SIZE),
        isOwnStory = isOwnStory,
        isSeen = isSeen,
    )

    private fun author(id: String, displayName: String, username: String) = HomePostAuthor(
        id = id,
        displayName = displayName,
        username = username,
        avatarUrl = image(seed = "story_$id", width = AVATAR_SIZE, height = AVATAR_SIZE),
        hasActiveStory = true,
    )

    private fun image(seed: String, width: Int, height: Int) =
        "https://picsum.photos/seed/wayfolk_$seed/$width/$height"

    private companion object {
        const val AVATAR_SIZE = 200
        const val POST_WIDTH = 1080
        const val POST_HEIGHT = 1350
        const val TOTAL_POSTS = 30
        const val LIKE_BASE = 120
        const val LIKE_STEP = 37
        const val COMMENT_BASE = 4
        val NETWORK_DELAY = 800.milliseconds
    }
}
