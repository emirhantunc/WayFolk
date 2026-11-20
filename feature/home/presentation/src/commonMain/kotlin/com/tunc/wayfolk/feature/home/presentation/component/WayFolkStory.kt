package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.logger.WayFolkLogger
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.stringResource
import com.tunc.wayfolk.uicomponent.asyncimage.WayFolkAsyncImage

private const val LOG_TAG = "WayFolkStory"

@Composable
fun WayFolkStory(
    url: String,
    modifier: Modifier = Modifier,
    imageSize: Dp = WayFolkStoryDefaults.ImageSize,
    onImageStateChange: (StoryImageState) -> Unit = {},
    loadingContent: @Composable BoxScope.() -> Unit = { WayFolkStoryDefaults.LoadingIndicator() },
    errorContent: @Composable BoxScope.() -> Unit = {},
) {
    var imageState by remember(url) { mutableStateOf(StoryImageState.Loading) }
    val currentOnImageStateChange by rememberUpdatedState(onImageStateChange)

    fun updateImageState(newState: StoryImageState) {
        imageState = newState
        currentOnImageStateChange(newState)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(WayFolkStoryDefaults.ContentSpacing),
    ) {
        Box(
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .background(AppThemeProvider.colors.surfaceContainerHigh.color),
            contentAlignment = Alignment.Center,
        ) {
            WayFolkAsyncImage(
                url = url,
                contentDescription = stringResource(WayFolkStrings.Home.storyImageDescription),
                modifier = Modifier.matchParentSize(),
                onLoading = { updateImageState(StoryImageState.Loading) },
                onSuccess = { state ->
                    updateImageState(StoryImageState.Success)
                    WayFolkLogger.d(message = "Loaded $url from ${state.result.dataSource}", tag = LOG_TAG)
                },
                onError = { state ->
                    updateImageState(StoryImageState.Error)
                    WayFolkLogger.e(e = state.result.throwable, tag = LOG_TAG)
                },
            )

            when (imageState) {
                StoryImageState.Loading -> loadingContent()
                StoryImageState.Error -> errorContent()
                StoryImageState.Success -> Unit
            }
        }

        WayFolkText(
            text = WayFolkStrings.Home.storyImageDescription,
            style = AppTypographyTokens.Caption.regular.textStyle,
        )
    }
}

object WayFolkStoryDefaults {

    val ImageSize: Dp = 72.dp
    val ContentSpacing: Dp = 4.dp
    val LoadingIndicatorSize: Dp = 24.dp
    val LoadingIndicatorStrokeWidth: Dp = 2.dp

    @Composable
    fun LoadingIndicator(modifier: Modifier = Modifier) {
        CircularProgressIndicator(
            modifier = modifier.size(LoadingIndicatorSize),
            color = AppThemeProvider.colors.primary.color,
            strokeWidth = LoadingIndicatorStrokeWidth,
        )
    }
}
