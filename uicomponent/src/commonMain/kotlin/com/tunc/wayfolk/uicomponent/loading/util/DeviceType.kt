package com.tunc.wayfolk.uicomponent.loading.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

private const val TABLET_DP = 768
private const val NORMAL_DP = 393

@Composable
fun rememberDeviceType(): DeviceType {
    val swDp = currentPlatformConfiguration().smallestScreenWidthDp

    return remember(swDp) {
        when {
            swDp >= TABLET_DP -> DeviceType.TABLET
            swDp >= NORMAL_DP -> DeviceType.NORMAL_PHONE
            else -> DeviceType.SMALL_PHONE
        }
    }
}

enum class DeviceType {
    SMALL_PHONE,
    NORMAL_PHONE,
    TABLET;

    val isSmallPhone: Boolean
        get() = this == SMALL_PHONE

    val isNormalPhone: Boolean
        get() = this == NORMAL_PHONE

    val isTablet: Boolean
        get() = this == TABLET
}