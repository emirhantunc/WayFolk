package com.tunc.android.wayfolk

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.android.wayfolk.navigation.WayFolkNavHost
import com.tunc.wayfolk.common.BottomNavigationState
import com.tunc.wayfolk.common.BottomNavigationStateManager
import com.tunc.wayfolk.common.GraphState
import com.tunc.wayfolk.feature.navigation.LocalWayFolkBottomNavigationStateManager
import com.tunc.wayfolk.feature.navigation.WayFolkBottomNavigationBar
import com.tunc.wayfolk.navigation.AppComposeNavigator
import com.tunc.wayfolk.navigation.NavigationTabEventManager
import com.tunc.wayfolk.navigation.StartDestinationProvider
import com.tunc.wayfolk.navigation.TopLevelBackStack
import com.tunc.wayfolk.navigation.model.ComposeNavManager
import com.tunc.wayfolk.navigation.model.NavigationCommand
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
            var bottomNavigationState by remember { mutableStateOf(BottomNavigationState.HIDE) }
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
                    bottomNavigationStateManager.stateFlow.collect { state ->
                        bottomNavigationState = state
                    }
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
            WayFolkNavHost(
                topLevelBackStack = topLevelBackStack,
                bottomBar = {
                    AnimatedVisibility(
                        visible = bottomNavigationState == BottomNavigationState.SHOW,
                        enter = expandVertically(expandFrom = Alignment.Top),
                        exit = shrinkVertically(shrinkTowards = Alignment.Top),
                    ) {
                        WayFolkBottomNavigationBar(
                            items = screenState.tabItems,
                            selectedRouter = topLevelBackStack.currentTab,
                            onItemClick = { item ->
                                viewModel.onAction(
                                    MainActivityContract.Action.OnSelectTab(item = item, sendEvent = true)
                                )
                                navigationManager.navigate(
                                    NavigationCommand.OpenScreen(
                                        screen = item.router,
                                        internPopBackStack = item.isInternPopBackStack
                                    )
                                )
                            }
                        )
                    }
                }
            )
        }
    }
}
