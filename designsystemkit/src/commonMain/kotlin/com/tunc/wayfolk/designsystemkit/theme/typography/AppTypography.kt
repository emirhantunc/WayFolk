package com.tunc.wayfolk.designsystemkit.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@ConsistentCopyVisibility
@Immutable
data class AppTypography internal constructor(
    val largeTitle: AppTypographyTokens.LargeTitle = AppTypographyTokens.LargeTitle,
    val title: AppTypographyTokens.Title = AppTypographyTokens.Title,
    val headline: AppTypographyTokens.Headline = AppTypographyTokens.Headline,
    val subheadline: AppTypographyTokens.Subheadline = AppTypographyTokens.Subheadline,
    val bodyLarge: AppTypographyTokens.BodyLarge = AppTypographyTokens.BodyLarge,
    val body: AppTypographyTokens.Body = AppTypographyTokens.Body,
    val bodySmall: AppTypographyTokens.BodySmall = AppTypographyTokens.BodySmall,
    val caption: AppTypographyTokens.Caption = AppTypographyTokens.Caption,
    val footnote: AppTypographyTokens.Footnote = AppTypographyTokens.Footnote,
    val overline: AppTypographyTokens.Overline = AppTypographyTokens.Overline,
    val micro: AppTypographyTokens.Micro = AppTypographyTokens.Micro,
)

internal val AppLocalTypography = staticCompositionLocalOf { AppTypography() }
