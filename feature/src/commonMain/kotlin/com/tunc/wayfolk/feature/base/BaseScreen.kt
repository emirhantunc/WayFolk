package com.tunc.wayfolk.feature.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.android.jvm.Empty
import com.tunc.wayfolk.common.BottomNavigationState
import com.tunc.wayfolk.common.connectivity.ConnectivityObserver
import com.tunc.wayfolk.contract.network.INetworkError
import com.tunc.wayfolk.contract.network.LoadingType
import com.tunc.wayfolk.contract.network.NetworkErrorType
import com.tunc.wayfolk.contract.network.NetworkState
import com.tunc.wayfolk.core.presentation.AppEventProvider
import com.tunc.wayfolk.core.presentation.CoreScreen
import com.tunc.wayfolk.core.presentation.ViewModelBehaviour
import com.tunc.wayfolk.core.presentation.appDimensionProvider
import com.tunc.wayfolk.core.presentation.util.currentPlatformActivity
import com.tunc.wayfolk.core.presentation.util.hideStatusBar
import com.tunc.wayfolk.core.presentation.util.showStatusBar
import com.tunc.wayfolk.core.presentation.util.statusBarsIgnoringVisibilityCompat
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.theme.color.WayFolkColor
import com.tunc.wayfolk.designsystemkit.theme.typography.AppTypographyTokens
import com.tunc.wayfolk.feature.navigation.appBottomNavigationStateManager
import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.common_no_internet
import com.tunc.wayfolk.uicomponent.loading.AppScreenLoading
import com.tunc.wayfolk.uicomponent.loading.AppScreenSkeleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
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
    shimmerResourceId: DrawableResource? = null,
    shimmerContentScale: ContentScale = ContentScale.Crop,
    shimmerStatusDuration: Int = SHIMMER_STATUS_CHANGE_DURATION,
    shimmerOverlayContent: @Composable ((Boolean) -> Unit)? = null,
    enableUserInteractions: Boolean = true,
    toolbar: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    screenName: String = String.Empty,
    connectivityObserver: ConnectivityObserver = koinInject(),
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
    val isConnected by connectivityObserver.isConnected.collectAsStateWithLifecycle()
    var noInternetError by remember { mutableStateOf(false) }
    val showNoInternet = !isConnected || noInternetError

    LaunchedEffect(isConnected) {
        if (isConnected) noInternetError = false
    }


    LifecycleStartEffect(Unit) {
        onStopOrDispose {
            keyboardManager?.hide()
        }
    }

    LaunchedEffect(showBottomAppBar) {
        if (showBottomAppBar) {
            navigationStateManager.updateState(BottomNavigationState.SHOW)
        } else {
            navigationStateManager.updateState(BottomNavigationState.HIDE)
        }
    }

    LaunchedEffect(owner, viewModel) {

        launch {
            viewModel.networkStateFlow.collect { networkState ->
                when (networkState) {
                    is NetworkState.Success -> Unit
                    is NetworkState.Error ->
                        handleServiceError(
                            error = networkState.error,
                            onNoInternet = {
                                noInternetError = true
                            },
                            handleGeneralError = {
                                networkError = networkState.error
                            }
                        )
                }
            }
        }

        launch {
            viewModel.toastStateFlow.collect { toastModel ->
                appEventProvider.showToast(toastModel)
            }
        }

        launch {
            viewModel.loadingStateFlow.collect { loadingModel ->
                when (loadingModel.loadingType) {
                    LoadingType.Content -> {
                        showContentLoading = loadingModel.show
                    }

                    LoadingType.Shimmer -> {
                        showShimmerLoading = loadingModel.show
                    }

                    LoadingType.Dialog -> {
                        appEventProvider.updateLoadingState(loadingModel.show)
                    }

                    LoadingType.Custom, LoadingType.None -> {
                        Unit
                    }
                }
            }
        }
    }

    LaunchedEffect(activity, showStatusBar) {
        if (showStatusBar) {
            activity.showStatusBar()
        } else {
            activity.hideStatusBar()
        }
    }

    val innerPaddings = appDimensionProvider.innerPaddings.value
    val localDirection = LocalLayoutDirection.current

    val statusBarPaddings = WindowInsets.statusBarsIgnoringVisibilityCompat.asPaddingValues()
    val navigationBarPaddings = WindowInsets.navigationBars.asPaddingValues()

    val screenPaddingValues = if (useCustomScreenPaddings) {
        customScreenPaddings
    } else {
        val bottomPadding = if (showBottomAppBar) {
            innerPaddings.calculateBottomPadding()
        } else {
            navigationBarPaddings.calculateBottomPadding()
        }

        PaddingValues(
            top = statusBarPaddings.calculateTopPadding(),
            start = innerPaddings.calculateStartPadding(localDirection),
            end = innerPaddings.calculateEndPadding(localDirection),
            bottom = bottomPadding
        )
    }

    var enableUserInteractionsOnScreen by remember(enableUserInteractions) {
        mutableStateOf(enableUserInteractions)
    }

    LaunchedEffect(enableUserInteractionsOnScreen) {
        if (!enableUserInteractionsOnScreen) {
            delay(timeMillis = INTERACTION_ENABLED_DELAY)
            enableUserInteractionsOnScreen = true
        }
    }

    toolbar?.let { toolbar() }

    if (enableContentLoading) {
        AppScreenLoading(
            show = showContentLoading,
            contentPadding = contentLoadingPaddings
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        CoreScreen(
            screenName = screenName,
            modifier = modifier
                .pointerInput(enableUserInteractionsOnScreen) {
                    if (!enableUserInteractionsOnScreen) {
                        awaitPointerEventScope {
                            while (true) {
                                awaitPointerEvent(pass = PointerEventPass.Initial)
                                    .changes.forEach { it.consume() }
                            }
                        }
                    }
                }
                .background(containerColor.color)
                .padding(screenPaddingValues),
        ) {
            shimmerResourceId?.let {
                AppScreenSkeleton (
                    showSkeleton = showShimmerLoading,
                    contentScale = shimmerContentScale,
                    shimmerStatusDuration = shimmerStatusDuration,
                    skeletonResourceId = it,
                    skeletonOverlayContent = shimmerOverlayContent
                ) {
                    content()
                }
            } ?: content()
        }

        NoInternetConnectionBar(
            visible = showNoInternet,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = screenPaddingValues.calculateBottomPadding()),
        )
    }

}


@Composable
private fun NoInternetConnectionBar(visible: Boolean, modifier: Modifier = Modifier) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
    ) {
        WayFolkText(
            text = Strings.string.common_no_internet,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(12.dp),
            style = AppTypographyTokens.Body.medium.textStyle,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}

private fun handleServiceError(
    error: INetworkError?,
    onNoInternet: () -> Unit,
    handleGeneralError: () -> Unit,
) {
    when (error?.code) {
        NetworkErrorType.UNAUTHORIZED.value -> {
            /**
             * will be handled on interceptor
             */
            return
        }

        NetworkErrorType.TOO_MANY_REQUESTS.value -> {
        }

        NetworkErrorType.NO_INTERNET_CONNECTION.value -> {
            onNoInternet()
        }

        NetworkErrorType.SECURITY_ERROR.value -> {
        }

        else -> {
            handleGeneralError()
        }
    }
}