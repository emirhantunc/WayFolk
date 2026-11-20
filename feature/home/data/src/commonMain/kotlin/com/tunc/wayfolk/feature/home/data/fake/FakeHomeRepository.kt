package com.tunc.wayfolk.feature.home.data.fake

import com.tunc.wayfolk.contract.network.RestResult
import com.tunc.wayfolk.feature.home.domain.model.HomeFeed
import com.tunc.wayfolk.feature.home.domain.model.HomePost
import com.tunc.wayfolk.feature.home.domain.model.HomePostAuthor
import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import com.tunc.wayfolk.feature.home.domain.repository.HomeRepository
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeHomeRepository(
    private val clock: Clock,
) : HomeRepository {

    override fun getFeed(): Flow<RestResult<HomeFeed>> = flow {
        emit(RestResult.Loading(true))
        emit(RestResult.Success(createFeed()))
        emit(RestResult.Loading(false))
    }

    private fun createFeed(): HomeFeed {
        val sofia = author(id = "sofia", displayName = "Sofia Andersen", username = "sofia.wanders")
        val marco = author(id = "marco", displayName = "Marco Reyes", username = "marco.roams")
        return HomeFeed(
            stories = listOf(
                story(id = "me", username = "me", isOwnStory = true, isSeen = true),
                story(id = "leila", username = "leila.m"),
                story(id = "tomasz", username = "tomasz_w"),
                story(id = "ana", username = "ana.travel"),
                story(id = "rei", username = "rei_jp"),
                story(id = "sofia", username = "sofia.wanders"),
                story(id = "marco", username = "marco.roams", isSeen = true),
            ),
            posts = listOf(
                HomePost(
                    id = "post_sofia_lofoten",
                    author = sofia,
                    location = "Lofoten Islands, Norway",
                    imageUrl = image(seed = "post_lofoten", width = 1080, height = 1350),
                    caption = "Woke up at 4am for this. Zero regrets. The silence here is something else. 🏔️",
                    likeCount = 2341,
                    commentCount = 48,
                    isLiked = false,
                    isSaved = false,
                    createdAtEpochMillis = ago(2.hours),
                ),
                HomePost(
                    id = "post_marco_jiufen",
                    author = marco,
                    location = "Jiufen, Taiwan",
                    imageUrl = image(seed = "post_jiufen", width = 1080, height = 1350),
                    caption = "Three weeks in Asia and still can't get enough of these mountain villages.",
                    likeCount = 1876,
                    commentCount = 31,
                    isLiked = true,
                    isSaved = false,
                    createdAtEpochMillis = ago(5.hours),
                ),
            ),
            hasUnreadMessages = true,
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

    private fun ago(duration: Duration): Long = (clock.now() - duration).toEpochMilliseconds()

    private companion object {
        const val AVATAR_SIZE = 200
    }
}
