package com.tunc.wayfolk.uicomponent.pagination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.extension.debounceClickable
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings

private val IndicatorSize = 24.dp
private val IndicatorStrokeWidth = 2.dp

@Composable
fun WayFolkPaginationFooter(
    isLoading: Boolean,
    hasError: Boolean,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AppThemeProvider.colors
    val spacing = AppThemeProvider.spacing

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = spacing.space200),
        contentAlignment = Alignment.Center,
    ) {
        when {
            isLoading -> CircularProgressIndicator(
                modifier = Modifier.size(IndicatorSize),
                color = colors.icon.color,
                trackColor = colors.divider.color,
                strokeWidth = IndicatorStrokeWidth,
            )

            hasError -> WayFolkText(
                text = WayFolkStrings.Common.loadMoreError,
                style = AppTypographyTokens.Body.medium.textStyle,
                color = colors.textSecondary.color,
                modifier = Modifier
                    .debounceClickable(onClick = onRetry)
                    .padding(spacing.space100),
            )
        }
    }
}
