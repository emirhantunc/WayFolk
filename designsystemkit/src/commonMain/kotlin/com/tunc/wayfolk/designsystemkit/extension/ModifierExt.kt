package com.tunc.wayfolk.designsystemkit.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.util.DesignSystemConstants.DEFAULT_DEBOUNCE_DELAY
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeSource


fun Modifier.noRippleClickable(enabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }.semantics {
    }
}


inline fun Modifier.conditionModifier(
    condition: Boolean,
    crossinline factory: () -> Modifier
): Modifier {
    return if (condition) {
        this.then(factory())
    } else {
        this
    }
}


fun Modifier.debounceClickable(
    debounceInterval: Long = DEFAULT_DEBOUNCE_DELAY,
    hasRipple: Boolean = false,
    enabled: Boolean = true,
    isAccessibilityGroup: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    val clickDebouncer = remember(debounceInterval) { ClickDebouncer(debounceInterval) }

    if (isAccessibilityGroup) {
        if (hasRipple) {
            clickable(
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                indication = ripple(
                    color = AppThemeProvider.colors.background.color
                )
            ) {
                if (clickDebouncer.tryClick()) onClick()
            }.semantics {
                role = Role.Button
            }
        } else {
            noRippleClickable {
                if (clickDebouncer.tryClick()) onClick()
            }.semantics {
                role = Role.Button
            }
        }
    } else {
        pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    if (enabled && clickDebouncer.tryClick()) onClick()
                }
            )
        }
    }
}

private class ClickDebouncer(debounceIntervalMillis: Long) {

    private val debounceInterval = debounceIntervalMillis.milliseconds
    private var lastClickMark: TimeSource.Monotonic.ValueTimeMark? = null

    fun tryClick(): Boolean {
        val lastMark = lastClickMark
        if (lastMark != null && lastMark.elapsedNow() < debounceInterval) return false
        lastClickMark = TimeSource.Monotonic.markNow()
        return true
    }
}
