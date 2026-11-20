package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.extension.debounceClickable
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.home.presentation.model.HomePostUiModel
import com.tunc.wayfolk.feature.home.presentation.util.formatWithGrouping
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.pluralStringResource
import com.tunc.wayfolk.multilanguage.language.stringResource
import com.tunc.wayfolk.multilanguage.text.asString
import com.tunc.wayfolk.uicomponent.asyncimage.WayFolkAsyncImage
import com.tunc.wayfolk.uicomponent.avatar.WayFolkRingAvatar
import com.tunc.wayfolk.uicomponent.avatar.WayFolkRingAvatarDefaults

private const val POST_IMAGE_ASPECT_RATIO = 4f / 5f
private val ActionIconSize = 24.dp
private val MoreIconSize = 20.dp
private val LocationIconSize = 12.dp
private val TimestampLetterSpacing = 0.25.sp

@Composable
internal fun HomePostCard(
    post: HomePostUiModel,
    onAuthorClick: () -> Unit,
    onMoreClick: () -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        PostHeader(post = post, onAuthorClick = onAuthorClick, onMoreClick = onMoreClick)
        PostImage(imageUrl = post.imageUrl, authorName = post.authorName)
        PostActions(
            isLiked = post.isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick,
            onSaveClick = onSaveClick,
        )
        PostDetails(post = post, onCommentsClick = onCommentClick)
    }
}

@Composable
private fun PostHeader(
    post: HomePostUiModel,
    onAuthorClick: () -> Unit,
    onMoreClick: () -> Unit,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.space150),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.debounceClickable(onClick = onAuthorClick),
            horizontalArrangement = Arrangement.spacedBy(spacing.space125),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WayFolkRingAvatar(
                imageUrl = post.authorAvatarUrl,
                contentDescription = null,
                size = WayFolkRingAvatarDefaults.SmallSize,
                ringWidth = WayFolkRingAvatarDefaults.SmallRingWidth,
                ring = post.authorRing,
            )
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                WayFolkText(
                    text = post.authorName,
                    style = AppTypographyTokens.Body.semiBold.textStyle,
                    color = colors.textPrimary.color,
                    maxLines = 1,
                )
                post.location?.let { location ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(spacing.space050),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = AppThemeProvider.icons.locationPin,
                            contentDescription = null,
                            tint = colors.textTertiary.color,
                            modifier = Modifier.size(LocationIconSize),
                        )
                        WayFolkText(
                            text = location,
                            style = AppTypographyTokens.Caption.regular.textStyle,
                            color = colors.textTertiary.color,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
        Icon(
            imageVector = AppThemeProvider.icons.moreHorizontal,
            contentDescription = stringResource(WayFolkStrings.Home.moreOptionsDescription),
            tint = colors.iconMuted.color,
            modifier = Modifier
                .padding(horizontal = spacing.space050)
                .size(MoreIconSize)
                .debounceClickable(onClick = onMoreClick),
        )
    }
}

@Composable
private fun PostImage(imageUrl: String, authorName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(POST_IMAGE_ASPECT_RATIO)
            .background(AppThemeProvider.colors.surfaceContainer.color),
    ) {
        WayFolkAsyncImage(
            url = imageUrl,
            contentDescription = stringResource(WayFolkStrings.Home.postImageDescription, authorName),
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Composable
private fun PostActions(
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val colors = AppThemeProvider.colors
    val icons = AppThemeProvider.icons
    val spacing = AppThemeProvider.spacing

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacing.space150, end = spacing.space150, top = spacing.space150),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.space200),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PostActionIcon(
                imageVector = if (isLiked) icons.heartFilled else icons.heart,
                contentDescription = stringResource(
                    if (isLiked) WayFolkStrings.Home.unlikeDescription else WayFolkStrings.Home.likeDescription
                ),
                tint = if (isLiked) colors.like.color else colors.icon.color,
                onClick = onLikeClick,
            )
            PostActionIcon(
                imageVector = icons.message,
                contentDescription = stringResource(WayFolkStrings.Home.commentDescription),
                onClick = onCommentClick,
            )
            PostActionIcon(
                imageVector = icons.send,
                contentDescription = stringResource(WayFolkStrings.Home.shareDescription),
                onClick = onShareClick,
            )
        }
        PostActionIcon(
            imageVector = icons.bookmark,
            contentDescription = stringResource(WayFolkStrings.Home.saveDescription),
            onClick = onSaveClick,
        )
    }
}

@Composable
private fun PostActionIcon(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = AppThemeProvider.colors.icon.color,
    size: Dp = ActionIconSize,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = tint,
        modifier = Modifier
            .size(size)
            .debounceClickable(onClick = onClick),
    )
}

@Composable
private fun PostDetails(
    post: HomePostUiModel,
    onCommentsClick: () -> Unit,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing
    val separator = stringResource(WayFolkStrings.Common.thousandsSeparator)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacing.space150, end = spacing.space150, top = spacing.space100, bottom = spacing.space200),
        verticalArrangement = Arrangement.spacedBy(spacing.space050),
    ) {
        WayFolkText(
            text = pluralStringResource(
                WayFolkStrings.Home.postLikes,
                post.likeCount,
                post.likeCount.formatWithGrouping(separator),
            ),
            style = AppTypographyTokens.Body.semiBold.textStyle,
            color = colors.textPrimary.color,
        )
        if (post.caption.isNotBlank()) {
            WayFolkText(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = colors.textPrimary.color)) {
                        append(post.authorUsername)
                    }
                    append(" ")
                    append(post.caption)
                },
                style = AppTypographyTokens.Body.regular.textStyle,
                color = colors.textBody.color,
            )
        }
        if (post.commentCount > 0) {
            WayFolkText(
                text = pluralStringResource(WayFolkStrings.Home.postViewComments, post.commentCount, post.commentCount),
                style = AppTypographyTokens.Caption.regular.textStyle,
                color = colors.textMuted.color,
                modifier = Modifier.debounceClickable(onClick = onCommentsClick),
            )
        }
        WayFolkText(
            text = post.timeAgo.asString().uppercase(),
            style = AppTypographyTokens.Micro.regular.textStyle.copy(letterSpacing = TimestampLetterSpacing),
            color = colors.textMuted.color,
        )
    }
}
