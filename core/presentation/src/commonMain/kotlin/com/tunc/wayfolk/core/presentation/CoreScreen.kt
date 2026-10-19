package com.tunc.wayfolk.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.core.presentation.util.ObserveLifecycleEvents
import com.tunc.wayfolk.logger.WayFolkLogger


@Composable
fun CoreScreen(
    modifier: Modifier = Modifier,
    screenName: String,
    content: @Composable ColumnScope.() -> Unit
) {
    ObserveLifecycleEvents { _, _, eventName ->
        logLifecycleEvents(eventName, screenName)
    }

    Column(
        modifier = modifier
    ) {
        content()
    }
}

private fun logLifecycleEvents(lifeCycleName: String, screenName: String) {
    WayFolkLogger.e(message = "life cycle event - $lifeCycleName $screenName")
}
