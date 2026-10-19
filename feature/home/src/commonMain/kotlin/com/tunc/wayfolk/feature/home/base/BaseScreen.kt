package com.tunc.wayfolk.feature.home.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.contract.network.INetworkError
import com.tunc.wayfolk.core.presentation.AppEventProvider
import com.tunc.wayfolk.core.presentation.ViewModelBehaviour
import com.tunc.wayfolk.core.presentation.appDimensionProvider
import com.tunc.wayfolk.core.presentation.util.currentPlatformActivity
import com.tunc.wayfolk.core.presentation.util.hideStatusBar
import com.tunc.wayfolk.core.presentation.util.showStatusBar
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.color.WayFolkColor
import com.tunc.wayfolk.feature.navigation.appBottomNavigationStateManager
import org.koin.compose.koinInject


private const val INTERACTION_ENABLED_DELAY = 2000L
private const val SHIMMER_STATUS_CHANGE_DURATION = 350


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModelBehaviour,
    showBottomAppBar: Boolean = false,
    showStatusBar: Boolean = true,
    enableContentLoading: Boolean = true,
    contentLoadingPaddings: PaddingValues = PaddingValues(vertical = 4.dp),
    customScreenPaddings: PaddingValues = PaddingValues(),
    containerColor: WayFolkColor = AppThemeProvider.colors.primaryContainer,
    useCustomScreenPaddings: Boolean = false,
    shimmerResourceId: Int? = null,
    shimmerContentScale: ContentScale = ContentScale.Crop,
    shimmerStatusDuration: Int = SHIMMER_STATUS_CHANGE_DURATION,
    shimmerOverlayContent: @Composable ((Boolean) -> Unit)? = null,
    enableUserInteractions: Boolean = true,
    toolbar: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    screenName: String = String.Empty
) {

    val owner = LocalLifecycleOwner.current
    val activity = currentPlatformActivity
    val appEventProvider: AppEventProvider = koinInject()
    val appDimensionProvider = appDimensionProvider
    val navigationStateManager = appBottomNavigationStateManager


    var networkError by remember { mutableStateOf<INetworkError?>(null) }
    var showContentLoading by remember { mutableStateOf(false) }
    var showShimmerLoading by remember { mutableStateOf(false) }
    val keyboardManager = LocalSoftwareKeyboardController.current

    LaunchedEffect(activity, showStatusBar) {
        if (showStatusBar) {
            activity.showStatusBar()
        } else {
            activity.hideStatusBar()
        }
    }

}