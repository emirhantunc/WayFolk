package com.tunc.wayfolk.designsystemkit.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.stringResource
import com.tunc.wayfolk.multilanguage.text.UiText
import com.tunc.wayfolk.multilanguage.text.asString

@Composable
fun WayFolkText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypographyTokens.LargeTitle.regular.textStyle,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}

@Composable
fun WayFolkText(
    text: WayFolkStringResource,
    vararg formatArgs: Any,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypographyTokens.LargeTitle.regular.textStyle,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
) {
    WayFolkText(
        text = stringResource(text, *formatArgs),
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}

@Composable
fun WayFolkText(
    text: UiText,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypographyTokens.LargeTitle.regular.textStyle,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
) {
    WayFolkText(
        text = text.asString(),
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}

@Composable
fun WayFolkText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTypographyTokens.LargeTitle.regular.textStyle,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}
