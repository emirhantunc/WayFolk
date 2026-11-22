package com.tunc.wayfolk.feature.profile.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.profile.presentation.util.ProfileConstants
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.uicomponent.emptystate.WayFolkEmptyState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = koinViewModel()

    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        screenName = ProfileConstants.Profile.NAME,
        viewModel = viewModel,
        showBottomAppBar = true,
        containerColor = AppThemeProvider.colors.background,
        content = {
            WayFolkEmptyState(
                icon = AppThemeProvider.icons.profile,
                title = WayFolkStrings.Profile.tabTitle,
                description = WayFolkStrings.Common.comingSoon,
            )
        }
    )
}
