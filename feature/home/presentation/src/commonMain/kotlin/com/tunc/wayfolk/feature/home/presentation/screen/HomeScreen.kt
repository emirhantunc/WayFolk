package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.home.presentation.component.WayFolkStory
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants
import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.home_empty_description
import com.tunc.wayfolk.multilanguage.home_empty_title
import com.tunc.wayfolk.multilanguage.home_stories_title
import com.tunc.wayfolk.multilanguage.home_title
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    BaseScreen(
        screenName = HomeConstants.Home.NAME,
        viewModel = viewModel,
        content = {
            WayFolkText(
                text = Strings.string.home_title,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            if (state.stories.isEmpty()) {
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
            } else {
                WayFolkText(
                    text = Strings.string.home_stories_title,
                    style = AppTypographyTokens.Headline.semiBold.textStyle,
                )
                LazyRow {
                    items(items = state.stories, key = { it.id }) { story ->
                        WayFolkStory(url = story.imageUrl)
                    }
                }
            }
        }
    )
}
