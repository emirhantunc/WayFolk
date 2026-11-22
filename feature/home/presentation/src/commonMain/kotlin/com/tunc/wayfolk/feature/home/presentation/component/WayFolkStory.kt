package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.extension.debounceClickable
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.logger.WayFolkLogger
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.stringResource
import com.tunc.wayfolk.uicomponent.avatar.WayFolkAvatarRing
import com.tunc.wayfolk.uicomponent.avatar.WayFolkRingAvatar
import com.tunc.wayfolk.uicomponent.avatar.WayFolkRingAvatarDefaults

private const val LOG_TAG = "WayFolkStory"

@Composable
fun WayFolkStory(
    imageUrl: String,
    username: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    ring: WayFolkAvatarRing = WayFolkAvatarRing.Active,
    avatarSize: Dp = WayFolkStoryDefaults.AvatarSize,
    onImageStateChange: (StoryImageState) -> Unit = {},
    loadingContent: @Composable BoxScope.() -> Unit = {},
    errorContent: @Composable BoxScope.() -> Unit = {},
) {
    var imageState by remember(imageUrl) { mutableStateOf(StoryImageState.Loading) }
    val currentOnImageStateChange by rememberUpdatedState(onImageStateChange)

    fun updateImageState(newState: StoryImageState) {
        imageState = newState
        currentOnImageStateChange(newState)
    }

    Column(
        modifier = modifier
            .width(avatarSize)
            .debounceClickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(WayFolkStoryDefaults.ContentSpacing),
    ) {
        WayFolkRingAvatar(
            imageUrl = imageUrl,
            contentDescription = stringResource(WayFolkStrings.Home.storyDescription, username),
            size = avatarSize,
            ringWidth = WayFolkRingAvatarDefaults.LargeRingWidth,
            ring = ring,
            onLoading = { updateImageState(StoryImageState.Loading) },
            onSuccess = { updateImageState(StoryImageState.Success) },
            onError = { state ->
                updateImageState(StoryImageState.Error)
                WayFolkLogger.e(e = state.result.throwable, tag = LOG_TAG)
            },
            overlay = {
                when (imageState) {
                    StoryImageState.Loading -> loadingContent()
                    StoryImageState.Error -> errorContent()
                    StoryImageState.Success -> Unit
                }
            },
        )

        WayFolkText(
            text = username,
            style = AppTypographyTokens.Footnote.regular.textStyle,
            color = AppThemeProvider.colors.textSecondary.color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

object WayFolkStoryDefaults {
    val AvatarSize: Dp = WayFolkRingAvatarDefaults.LargeSize
    val ContentSpacing: Dp = 6.dp
}
