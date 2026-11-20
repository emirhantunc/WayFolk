package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.platform.testTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import coil3.ColorImage
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.decode.DataSource
import coil3.request.SuccessResult
import coil3.test.FakeImageLoaderEngine
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.getString
import java.util.concurrent.ConcurrentLinkedQueue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(DelicateCoilApi::class)
@RunWith(AndroidJUnit4::class)
class WayFolkStoryTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val requestedData = ConcurrentLinkedQueue<Any>()
    private val reportedStates = ConcurrentLinkedQueue<StoryImageState>()
    private val slowImageGate = CompletableDeferred<Unit>()

    private val storyImageDescription: String by lazy {
        runBlocking { getString(WayFolkStrings.Home.storyDescription, USERNAME) }
    }

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val storyImage = ColorImage(color = Color.Red.toArgb(), width = IMAGE_SIZE, height = IMAGE_SIZE)
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(
                predicate = { data ->
                    requestedData += data
                    data == STORY_URL
                },
                image = storyImage,
            )
            .intercept(
                predicate = { data -> data == SLOW_STORY_URL },
                interceptor = { chain ->
                    slowImageGate.await()
                    SuccessResult(image = storyImage, request = chain.request, dataSource = DataSource.NETWORK)
                },
            )
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .components { add(engine) }
            .build()
        SingletonImageLoader.setUnsafe(imageLoader)
    }

    @After
    fun tearDown() {
        SingletonImageLoader.reset()
    }

    @Test
    fun showsUsername() {
        composeRule.setContent { WayFolkStory(imageUrl = STORY_URL, username = USERNAME, onClick = {}) }

        composeRule.onNodeWithText(USERNAME).assertIsDisplayed()
    }

    @Test
    fun imageHasLocalizedContentDescription() {
        composeRule.setContent { WayFolkStory(imageUrl = STORY_URL, username = USERNAME, onClick = {}) }

        composeRule.onNodeWithContentDescription(storyImageDescription).assertExists()
    }

    @Test
    fun requestsImageForGivenUrl() {
        composeRule.setContent { WayFolkStory(imageUrl = STORY_URL, username = USERNAME, onClick = {}) }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { requestedData.isNotEmpty() }

        assertEquals(listOf<Any>(STORY_URL), requestedData.distinct())
    }

    @Test
    fun displaysImageAfterLoading() {
        composeRule.setContent { WayFolkStory(imageUrl = STORY_URL, username = USERNAME, onClick = {}) }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { requestedData.isNotEmpty() }
        composeRule.waitForIdle()

        composeRule.onNodeWithContentDescription(storyImageDescription).assertIsDisplayed()
    }

    @Test
    fun reportsSuccessWhenImageLoads() {
        composeRule.setContent {
            WayFolkStory(
                imageUrl = STORY_URL,
                username = USERNAME,
                onClick = {},
                onImageStateChange = { reportedStates += it },
            )
        }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { reportedStates.contains(StoryImageState.Success) }

        assertEquals(StoryImageState.Success, reportedStates.last())
        assertEquals(false, reportedStates.contains(StoryImageState.Error))
    }

    @Test
    fun reportsErrorAndShowsErrorContentWhenImageFails() {
        composeRule.setContent {
            WayFolkStory(
                imageUrl = MISSING_STORY_URL,
                username = USERNAME,
                onClick = {},
                onImageStateChange = { reportedStates += it },
                errorContent = { Box(modifier = Modifier.testTag(ERROR_TAG)) },
            )
        }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { reportedStates.contains(StoryImageState.Error) }

        composeRule.onNodeWithTag(ERROR_TAG, useUnmergedTree = true).assertExists()
    }

    @Test
    fun showsLoadingContentUntilImageLoads() {
        composeRule.setContent {
            WayFolkStory(
                imageUrl = SLOW_STORY_URL,
                username = USERNAME,
                onClick = {},
                onImageStateChange = { reportedStates += it },
                loadingContent = { Box(modifier = Modifier.testTag(LOADING_TAG)) },
            )
        }

        composeRule.onNodeWithTag(LOADING_TAG, useUnmergedTree = true).assertExists()

        slowImageGate.complete(Unit)
        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { reportedStates.contains(StoryImageState.Success) }

        composeRule.onNodeWithTag(LOADING_TAG, useUnmergedTree = true).assertDoesNotExist()
    }

    private companion object {
        const val STORY_URL = "https://example.com/story.jpg"
        const val USERNAME = "ana.travel"
        const val SLOW_STORY_URL = "https://example.com/slow-story.jpg"
        const val MISSING_STORY_URL = "https://example.com/missing-story.jpg"
        const val LOADING_TAG = "story_loading"
        const val ERROR_TAG = "story_error"
        const val IMAGE_SIZE = 100
        const val TIMEOUT_MILLIS = 5_000L
    }
}
