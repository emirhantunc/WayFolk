package com.tunc.wayfolk.feature.rooms.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.rooms.presentation.util.RoomsConstants
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.uicomponent.emptystate.WayFolkEmptyState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RoomsScreen() {
    val viewModel: RoomsViewModel = koinViewModel()

    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        screenName = RoomsConstants.Rooms.NAME,
        viewModel = viewModel,
        showBottomAppBar = true,
        containerColor = AppThemeProvider.colors.background,
        content = {
            WayFolkEmptyState(
                icon = AppThemeProvider.icons.rooms,
                title = WayFolkStrings.Rooms.tabTitle,
                description = WayFolkStrings.Common.comingSoon,
            )
        }
    )
}
