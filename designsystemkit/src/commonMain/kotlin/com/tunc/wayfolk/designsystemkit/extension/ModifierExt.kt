package com.tunc.wayfolk.designsystemkit.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.designsystemkit.util.DesignSystemConstants.DEFAULT_DEBOUNCE_DELAY


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
    var lastClickTime by remember { mutableLongStateOf(0L) }

    if (isAccessibilityGroup) {
        if (hasRipple) {
            clickable(
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                indication = ripple(
                    color = AppThemeProvider.colors.background.color
                )
            ) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < debounceInterval) return@clickable

                lastClickTime = currentTime
                onClick()
            }.semantics {
                role = Role.Button
            }
        } else {
            noRippleClickable {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < debounceInterval) return@noRippleClickable

                lastClickTime = currentTime
                onClick()
            }.semantics {
                role = Role.Button
            }
        }
    } else {
        pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    if (enabled) {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastClickTime < debounceInterval) return@detectTapGestures

                        lastClickTime = currentTime
                        onClick()
                    }
                }
            )
        }
    }
}