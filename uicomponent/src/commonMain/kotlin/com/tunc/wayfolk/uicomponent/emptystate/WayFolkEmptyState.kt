package com.tunc.wayfolk.uicomponent.emptystate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource

private val EmptyStateIconSize = 48.dp

@Composable
fun WayFolkEmptyState(
    icon: ImageVector,
    title: WayFolkStringResource,
    description: WayFolkStringResource,
    modifier: Modifier = Modifier,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = spacing.space300),
        verticalArrangement = Arrangement.spacedBy(spacing.space150, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colors.iconMuted.color,
            modifier = Modifier.size(EmptyStateIconSize),
        )
        WayFolkText(
            text = title,
            style = AppTypographyTokens.Title.semiBold.textStyle,
            color = colors.textPrimary.color,
            textAlign = TextAlign.Center,
        )
        WayFolkText(
            text = description,
            style = AppTypographyTokens.Body.regular.textStyle,
            color = colors.textTertiary.color,
            textAlign = TextAlign.Center,
        )
    }
}
