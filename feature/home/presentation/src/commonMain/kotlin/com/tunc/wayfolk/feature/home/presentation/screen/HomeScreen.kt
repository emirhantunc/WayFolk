package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants
import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.home_empty_description
import com.tunc.wayfolk.multilanguage.home_empty_title
import com.tunc.wayfolk.multilanguage.home_title


@Composable
fun HomeScreen() {

    BaseScreen(
        screenName = HomeConstants.Home.NAME,
        viewModel = HomeViewModel(),
        content = {
            WayFolkText(
                text = Strings.string.home_title,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            WayFolkText(
                text = Strings.string.home_empty_title,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = AppTypographyTokens.Headline.semiBold.textStyle,
            )
            WayFolkText(
                text = Strings.string.home_empty_description,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = AppTypographyTokens.Body.regular.textStyle,
            )
        }
    )
}
