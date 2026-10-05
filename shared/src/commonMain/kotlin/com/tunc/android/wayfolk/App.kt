package com.tunc.android.wayfolk

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.tunc.wayfolk.navigation.AppComposeNavigator
import com.tunc.wayfolk.navigation.NavigationTabEventManager
import com.tunc.wayfolk.navigation.StartDestinationProvider
import org.koin.compose.koinInject

@Composable
fun App() {
    val startDestinationProvider: StartDestinationProvider = koinInject()
    val navigationTabEventManager: NavigationTabEventManager = koinInject()
    val appComposeNavigator: AppComposeNavigator = koinInject()
    MaterialTheme {

    }
}
