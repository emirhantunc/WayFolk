package com.tunc.wayfolk.feature.map.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.map.presentation.util.MapConstants
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.uicomponent.emptystate.WayFolkEmptyState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MapScreen() {
    val viewModel: MapViewModel = koinViewModel()

    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        screenName = MapConstants.Map.NAME,
        viewModel = viewModel,
        showBottomAppBar = true,
        containerColor = AppThemeProvider.colors.background,
        content = {
            WayFolkEmptyState(
                icon = AppThemeProvider.icons.map,
                title = WayFolkStrings.Map.tabTitle,
                description = WayFolkStrings.Common.comingSoon,
            )
        }
    )
}
