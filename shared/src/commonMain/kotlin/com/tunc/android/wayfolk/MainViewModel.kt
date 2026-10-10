package com.tunc.android.wayfolk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunc.wayfolk.common.GraphState
import com.tunc.wayfolk.navigation.home.HomeNavigation
import com.tunc.wayfolk.navigation.model.NavigationTabProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val SPLASH_DURATION = 2000L

class MainViewModel(
    navigationTabProvider: NavigationTabProvider,
    private val homeScreenNavigation: HomeNavigation
) : ViewModel() {
    private val _state = MutableStateFlow(
        MainActivityContract.State(
            tabItems = navigationTabProvider.getNavigationTabItems(),
            startDestination = navigationTabProvider.getStartDestination(),
            selectedTabRoute = navigationTabProvider.getNavigationTabItems().first().router
        )
    )

    val state: StateFlow<MainActivityContract.State> = _state.asStateFlow()

    init {
        initializeApp()
    }

    fun onAction(action: MainActivityContract.Action) {
        when (action) {
            is MainActivityContract.Action.OnSelectTab -> {
                _state.update { state ->
                    state.copy(selectedTabRoute = action.item.router)
                }
            }
        }
    }

    private fun initializeApp() {
        viewModelScope.launch {
            delay(SPLASH_DURATION.milliseconds)
            _state.update { state ->
                state.copy(
                    isSdksInitialized = true,
                    graphState = GraphState.StartHomeScreen
                )
            }
            homeScreenNavigation.navigateToHome(clearBackStack = true)
        }
    }
}
