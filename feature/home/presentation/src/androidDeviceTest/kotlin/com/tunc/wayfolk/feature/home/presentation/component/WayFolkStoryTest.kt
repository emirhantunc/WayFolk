package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import coil3.ColorImage
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.annotation.DelicateCoilApi
import coil3.test.FakeImageLoaderEngine
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.getString
import java.util.concurrent.ConcurrentLinkedQueue
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

    private val storyImageDescription: String by lazy {
        runBlocking { getString(WayFolkStrings.Home.storyImageDescription) }
    }

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val engine = FakeImageLoaderEngine.Builder()
            .intercept(
                predicate = { data ->
                    requestedData += data
                    data == STORY_URL
                },
                image = ColorImage(color = Color.Red.toArgb(), width = IMAGE_SIZE, height = IMAGE_SIZE),
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
    fun showsStoryImageDescriptionText() {
        composeRule.setContent { WayFolkStory(url = STORY_URL) }

        composeRule.onNodeWithText(storyImageDescription).assertIsDisplayed()
    }

    @Test
    fun imageHasLocalizedContentDescription() {
        composeRule.setContent { WayFolkStory(url = STORY_URL) }

        composeRule.onNodeWithContentDescription(storyImageDescription).assertExists()
    }

    @Test
    fun requestsImageForGivenUrl() {
        composeRule.setContent { WayFolkStory(url = STORY_URL) }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { requestedData.isNotEmpty() }

        assertEquals(listOf<Any>(STORY_URL), requestedData.distinct())
    }

    @Test
    fun displaysImageAfterLoading() {
        composeRule.setContent { WayFolkStory(url = STORY_URL) }

        composeRule.waitUntil(timeoutMillis = TIMEOUT_MILLIS) { requestedData.isNotEmpty() }
        composeRule.waitForIdle()

        composeRule.onNodeWithContentDescription(storyImageDescription).assertIsDisplayed()
    }

    private companion object {
        const val STORY_URL = "https://example.com/story.jpg"
        const val IMAGE_SIZE = 100
        const val TIMEOUT_MILLIS = 5_000L
    }
}
