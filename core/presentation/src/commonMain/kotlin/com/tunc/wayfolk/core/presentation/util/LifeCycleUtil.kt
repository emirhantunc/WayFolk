package com.tunc.wayfolk.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_ANY
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun ObserveLifecycleEvents(
    lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (owner: LifecycleOwner, event: Lifecycle.Event, eventName: String) -> Unit
) {
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            val eventName = when (event) {
                ON_CREATE -> "onCreate"
                ON_START -> "onStart"
                ON_RESUME -> "onResume"
                ON_PAUSE -> "onPause"
                ON_STOP -> "onStop"
                ON_DESTROY -> "onDestroy"
                ON_ANY -> ""
            }

            onEvent(source, event, eventName)
        }

        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
            onEvent(lifeCycleOwner, Lifecycle.Event.ON_DESTROY, "onDestroy")
        }
    }
}