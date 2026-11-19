package com.tunc.wayfolk.designsystemkit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider.icons
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider.radius
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider.spacing
import com.tunc.wayfolk.designsystemkit.theme.color.AppLocalColorScheme
import com.tunc.wayfolk.designsystemkit.theme.color.WayFolkColorScheme
import com.tunc.wayfolk.designsystemkit.theme.color.appDarkColors
import com.tunc.wayfolk.designsystemkit.theme.color.appLightColors
import com.tunc.wayfolk.designsystemkit.theme.color.toMaterialColorScheme
import com.tunc.wayfolk.designsystemkit.theme.icons.AppIcons
import com.tunc.wayfolk.designsystemkit.theme.icons.AppLocalIcons
import com.tunc.wayfolk.designsystemkit.theme.radius.AppLocalRadius
import com.tunc.wayfolk.designsystemkit.theme.radius.AppRadius
import com.tunc.wayfolk.designsystemkit.theme.spacing.AppLocalSpacing
import com.tunc.wayfolk.designsystemkit.theme.spacing.AppSpacing
import com.tunc.wayfolk.designsystemkit.theme.typography.AppLocalTypography
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypography

@Composable
fun AppTheme(
    typography: AppTypography = AppThemeProvider.typography,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        appDarkColors()
    } else {
        appLightColors()
    }

    CompositionLocalProvider(
        AppLocalColorScheme provides colors,
        AppLocalTypography provides typography,
        AppLocalIcons provides icons,
        AppLocalRadius provides radius,
        AppLocalSpacing provides spacing,
        LocalMinimumInteractiveComponentSize provides 0.dp
    ) {
        MaterialTheme(
            colorScheme = colors.toMaterialColorScheme(darkTheme),
            content = content
        )
    }
}

object AppThemeProvider {
    val colors: WayFolkColorScheme
        @Composable
        @ReadOnlyComposable
        get() = AppLocalColorScheme.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = AppLocalTypography.current

    val icons: AppIcons
        @Composable
        @ReadOnlyComposable
        get() = AppLocalIcons.current

    val radius: AppRadius
        @Composable
        @ReadOnlyComposable
        get() = AppLocalRadius.current

    val spacing: AppSpacing
        @Composable
        @ReadOnlyComposable
        get() = AppLocalSpacing.current
}
