package com.tunc.wayfolk.feature.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.navigation.model.BottomNavigationItem
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import org.jetbrains.compose.resources.painterResource

private val BarHeight = 56.dp
private val TabIconSize = 24.dp
private val DividerThickness = 1.dp

@Composable
fun WayFolkBottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedRouter: ComposeTabScreenRouter?,
    onItemClick: (BottomNavigationItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppThemeProvider.colors

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surface.color)
            .windowInsetsPadding(WindowInsets.navigationBars),
    ) {
        HorizontalDivider(thickness = DividerThickness, color = colors.divider.color)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(BarHeight)
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEach { item ->
                WayFolkBottomNavigationTab(
                    item = item,
                    selected = item.router == selectedRouter,
                    onClick = { onItemClick(item) },
                )
            }
        }
    }
}

@Composable
private fun RowScope.WayFolkBottomNavigationTab(
    item: BottomNavigationItem,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing

    val iconColor by animateColorAsState(
        targetValue = if (selected) colors.icon.color else colors.iconMuted.color,
    )
    val labelColor by animateColorAsState(
        targetValue = if (selected) colors.textPrimary.color else colors.textTertiary.color,
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            )
            .testTag(item.accessibilityId),
        verticalArrangement = Arrangement.spacedBy(spacing.space050, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(if (selected) item.icon else item.unSelectedIcon),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(TabIconSize),
        )
        WayFolkText(
            text = item.title,
            style = if (selected) {
                AppTypographyTokens.Caption.semiBold.textStyle
            } else {
                AppTypographyTokens.Caption.medium.textStyle
            },
            color = labelColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
