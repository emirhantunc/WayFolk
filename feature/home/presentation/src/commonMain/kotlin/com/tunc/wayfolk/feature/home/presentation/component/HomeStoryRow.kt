package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.home.presentation.model.HomeStoryUiModel
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.multilanguage.text.asString
import kotlinx.collections.immutable.ImmutableList

private const val STORY_CONTENT_TYPE = "story"

@Composable
internal fun HomeStoryRow(
    stories: ImmutableList<HomeStoryUiModel>,
    onStoryClick: (storyId: String) -> Unit,
    onStoryImageStateChange: (storyId: String, imageState: StoryImageState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = AppThemeProvider.spacing

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = spacing.space200, vertical = spacing.space150),
        horizontalArrangement = Arrangement.spacedBy(spacing.space200),
    ) {
        items(
            items = stories,
            key = { story -> story.id },
            contentType = { STORY_CONTENT_TYPE },
        ) { story ->
            WayFolkStory(
                imageUrl = story.imageUrl,
                username = story.username.asString(),
                ring = story.ring,
                onClick = { onStoryClick(story.id) },
                onImageStateChange = { imageState -> onStoryImageStateChange(story.id, imageState) },
            )
        }
    }
}
