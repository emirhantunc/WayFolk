package com.tunc.wayfolk.core.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf


private val LocalCoreAppDimensionProvider: ProvidableCompositionLocal<AppDimensionProvider> =
    staticCompositionLocalOf {
        AppDimensionProvider()
    }
val appDimensionProvider: AppDimensionProvider
    @Composable
    @ReadOnlyComposable
    get() = LocalCoreAppDimensionProvider.current

class AppDimensionProvider {
    var innerPaddings = mutableStateOf<PaddingValues>(PaddingValues())
        private set

    fun updateInnerPaddings(paddings: PaddingValues) {
        innerPaddings.value = paddings
    }
}