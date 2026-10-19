package com.tunc.wayfolk.designsystemkit.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.tunc.wayfolk.designsystemkit.theme.AppTextStyle

object AppTypographyTokens {
    object LargeTitle {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.LargeTitleSize,
            lineHeight = AppTypeScaleTokens.LargeTitleLineHeight,
            letterSpacing = AppTypeScaleTokens.LargeTitleLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Title {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.TitleSize,
            lineHeight = AppTypeScaleTokens.TitleLineHeight,
            letterSpacing = AppTypeScaleTokens.TitleLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Headline {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.HeadlineSize,
            lineHeight = AppTypeScaleTokens.HeadlineLineHeight,
            letterSpacing = AppTypeScaleTokens.HeadlineLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Subheadline {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.SubheadlineSize,
            lineHeight = AppTypeScaleTokens.SubheadlineLineHeight,
            letterSpacing = AppTypeScaleTokens.SubheadlineLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object BodyLarge {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.BodyLargeSize,
            lineHeight = AppTypeScaleTokens.BodyLargeLineHeight,
            letterSpacing = AppTypeScaleTokens.BodyLargeLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Body {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.BodySize,
            lineHeight = AppTypeScaleTokens.BodyLineHeight,
            letterSpacing = AppTypeScaleTokens.BodyLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object BodySmall {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.BodySmallSize,
            lineHeight = AppTypeScaleTokens.BodySmallLineHeight,
            letterSpacing = AppTypeScaleTokens.BodySmallLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Caption {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.CaptionSize,
            lineHeight = AppTypeScaleTokens.CaptionLineHeight,
            letterSpacing = AppTypeScaleTokens.CaptionLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Footnote {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.FootnoteSize,
            lineHeight = AppTypeScaleTokens.FootnoteLineHeight,
            letterSpacing = AppTypeScaleTokens.FootnoteLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Overline {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.OverlineSize,
            lineHeight = AppTypeScaleTokens.OverlineLineHeight,
            letterSpacing = AppTypeScaleTokens.OverlineLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }

    object Micro {
        private val baseStyle = TextStyle.Default.copy(
            fontSize = AppTypeScaleTokens.MicroSize,
            lineHeight = AppTypeScaleTokens.MicroLineHeight,
            letterSpacing = AppTypeScaleTokens.MicroLetterSpacing,
            fontFamily = FontFamily.Default
        )

        val regular: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Normal))

        val medium: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.Medium))

        val semiBold: AppTextStyle
            @Composable
            get() = AppTextStyle(baseStyle.copy(fontWeight = FontWeight.SemiBold))
    }
}
