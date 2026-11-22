package com.tunc.wayfolk.feature.search.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.search.presentation.util.SearchConstants
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.uicomponent.emptystate.WayFolkEmptyState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = koinViewModel()

    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        screenName = SearchConstants.Search.NAME,
        viewModel = viewModel,
        showBottomAppBar = true,
        containerColor = AppThemeProvider.colors.background,
        content = {
            WayFolkEmptyState(
                icon = AppThemeProvider.icons.search,
                title = WayFolkStrings.Search.tabTitle,
                description = WayFolkStrings.Common.comingSoon,
            )
        }
    )
}
