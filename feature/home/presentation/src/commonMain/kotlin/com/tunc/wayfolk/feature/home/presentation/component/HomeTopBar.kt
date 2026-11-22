package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.extension.debounceClickable
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.pluralStringResource
import com.tunc.wayfolk.multilanguage.language.stringResource

private val TopBarIconSize = 24.dp
private val UnreadBadgeMinSize = 16.dp
private val UnreadBadgeOffsetX = 8.dp
private val UnreadBadgeOffsetY = (-6).dp
private const val MAX_UNREAD_BADGE_COUNT = 99

@Composable
internal fun HomeTopBar(
    unreadMessagesCount: Int,
    onNotificationsClick: () -> Unit,
    onMessagesClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surface.color)
            .padding(
                start = spacing.space200,
                end = spacing.space200,
                top = spacing.space050,
                bottom = spacing.space150,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WayFolkText(
            text = WayFolkStrings.Home.topBarTitle,
            style = AppTypographyTokens.Title.semiBold.textStyle,
            color = colors.textPrimary.color,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.space200),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = AppThemeProvider.icons.heart,
                contentDescription = stringResource(WayFolkStrings.Home.notificationsDescription),
                tint = colors.icon.color,
                modifier = Modifier
                    .size(TopBarIconSize)
                    .debounceClickable(onClick = onNotificationsClick),
            )
            Box {
                val messagesDescription = if (unreadMessagesCount > 0) {
                    pluralStringResource(
                        WayFolkStrings.Home.unreadMessagesDescription,
                        unreadMessagesCount,
                        unreadMessagesCount,
                    )
                } else {
                    stringResource(WayFolkStrings.Home.messagesDescription)
                }
                Icon(
                    imageVector = AppThemeProvider.icons.message,
                    contentDescription = messagesDescription,
                    tint = colors.icon.color,
                    modifier = Modifier
                        .size(TopBarIconSize)
                        .debounceClickable(onClick = onMessagesClick),
                )
                if (unreadMessagesCount > 0) {
                    UnreadMessagesBadge(
                        count = unreadMessagesCount,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = UnreadBadgeOffsetX, y = UnreadBadgeOffsetY),
                    )
                }
            }
        }
    }
}

@Composable
private fun UnreadMessagesBadge(
    count: Int,
    modifier: Modifier = Modifier,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing
    val label = if (count > MAX_UNREAD_BADGE_COUNT) "$MAX_UNREAD_BADGE_COUNT+" else count.toString()

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = UnreadBadgeMinSize, minHeight = UnreadBadgeMinSize)
            .background(colors.textPrimary.color, RoundedCornerShape(AppThemeProvider.radius.radiusFull))
            .padding(horizontal = spacing.space050),
        contentAlignment = Alignment.Center,
    ) {
        WayFolkText(
            text = label,
            style = AppTypographyTokens.Micro.semiBold.textStyle,
            color = colors.onPrimary.color,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}
