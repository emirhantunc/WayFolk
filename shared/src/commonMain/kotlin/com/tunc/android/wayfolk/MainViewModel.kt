package com.tunc.android.wayfolk

import androidx.lifecycle.ViewModel
import com.tunc.wayfolk.navigation.home.HomeNavigation
import com.tunc.wayfolk.navigation.model.NavigationTabProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


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


    fun onAction(action: MainActivityContract.Action) {
        when (action) {
            is MainActivityContract.Action.OnGraphStateChange -> {
                _state.update { state ->
                    state.copy(graphState = action.graphState)
                }
            }

            is MainActivityContract.Action.OnSelectTab -> {
                _state.update { state ->
                    state.copy(selectedTabRoute = action.item.router)
                }
            }
        }
    }
}