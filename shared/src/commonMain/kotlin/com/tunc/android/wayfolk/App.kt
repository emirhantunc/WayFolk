package com.tunc.android.wayfolk

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.android.wayfolk.navigation.WayFolkNavHost
import com.tunc.wayfolk.common.BottomNavigationStateManager
import com.tunc.wayfolk.common.GraphState
import com.tunc.wayfolk.feature.navigation.LocalWayFolkBottomNavigationStateManager
import com.tunc.wayfolk.navigation.AppComposeNavigator
import com.tunc.wayfolk.navigation.NavigationTabEventManager
import com.tunc.wayfolk.navigation.StartDestinationProvider
import com.tunc.wayfolk.navigation.TopLevelBackStack
import com.tunc.wayfolk.navigation.model.ComposeNavManager
import com.tunc.wayfolk.navigation.model.TabEvent
import com.tunc.wayfolk.navigation.model.router.ComposeTabScreenRouter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    viewModel: MainViewModel = koinViewModel(),
    startDestinationProvider: StartDestinationProvider = koinInject(),
    navigationTabEventManager: NavigationTabEventManager = koinInject(),
    appComposeNavigator: AppComposeNavigator = koinInject(),
    navigationManager: ComposeNavManager = koinInject(),
    bottomNavigationStateManager: BottomNavigationStateManager = koinInject()
) {
    MaterialTheme {
        CompositionLocalProvider(
            LocalWayFolkBottomNavigationStateManager provides bottomNavigationStateManager
        ) {
            val screenState by viewModel.state.collectAsStateWithLifecycle()
            val topLevelBackStack = remember {
                TopLevelBackStack(
                    startKey = screenState.startDestination,
                    homeTab = startDestinationProvider.homeDestination as ComposeTabScreenRouter,
                    onReselectTabAtRoot = { router ->
                        navigationTabEventManager.sendEvent(TabEvent.OnClickAtRoot(router))
                    },
                    onExit = { //
                    }
                )
            }
            LaunchedEffect(Unit) {
                launch {
                    appComposeNavigator.handleNavigationCommands(
                        backStack = topLevelBackStack,
                        navigationCommands = navigationManager.navigationFlow,
                    )
                }

                launch {
                    bottomNavigationStateManager.effectFlow.collectLatest { graphState ->
                        when (graphState) {
                            GraphState.StartSplash -> {
                                topLevelBackStack.setRoot(startDestinationProvider.splashDestination)
                            }

                            GraphState.StartHomeScreen -> {
                                topLevelBackStack.setRoot(startDestinationProvider.homeDestination)
                            }
                        }

                        viewModel.onAction(
                            MainActivityContract.Action.OnGraphStateChange(graphState)
                        )
                    }
                }
            }
            WayFolkNavHost(topLevelBackStack = topLevelBackStack)
        }
    }
}
