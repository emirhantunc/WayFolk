package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.extension.debounceClickable
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.stringResource

private val TopBarIconSize = 24.dp
private val UnreadDotSize = 8.dp
private val UnreadDotOffsetX = 18.dp
private val UnreadDotOffsetY = (-2).dp

@Composable
internal fun HomeTopBar(
    hasUnreadMessages: Boolean,
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
                Icon(
                    imageVector = AppThemeProvider.icons.message,
                    contentDescription = stringResource(WayFolkStrings.Home.messagesDescription),
                    tint = colors.icon.color,
                    modifier = Modifier
                        .size(TopBarIconSize)
                        .debounceClickable(onClick = onMessagesClick),
                )
                if (hasUnreadMessages) {
                    Box(
                        modifier = Modifier
                            .offset(x = UnreadDotOffsetX, y = UnreadDotOffsetY)
                            .size(UnreadDotSize)
                            .background(colors.textPrimary.color, CircleShape),
                    )
                }
            }
        }
    }
}
