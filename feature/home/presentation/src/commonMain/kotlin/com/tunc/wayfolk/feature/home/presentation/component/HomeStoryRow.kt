package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.feature.home.domain.model.HomeStory
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import kotlinx.collections.immutable.ImmutableList

private const val STORY_CONTENT_TYPE = "story"

@Composable
internal fun HomeStoryRow(
    stories: ImmutableList<HomeStory>,
    onStoryImageStateChange: (storyId: String, imageState: StoryImageState) -> Unit,
    modifier: Modifier = Modifier,
    storySize: Dp = 55.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(
            items = stories,
            key = { story -> story.id },
            contentType = { STORY_CONTENT_TYPE },
        ) { story ->
            WayFolkStory(
                url = story.imageUrl,
                imageSize = storySize,
                onImageStateChange = { imageState -> onStoryImageStateChange(story.id, imageState) },
            )
        }
    }
}
