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
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    BaseScreen(
        screenName = HomeConstants.Home.NAME,
        viewModel = viewModel,
        content = {
            WayFolkStory(
                url = "https://img.magnific.com/free-photo/portrait-white-man-isolated_53876-40306.jpg?semt=ais_hybrid&w=740&q=80",
            )
        }
    )
}
