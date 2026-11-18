package com.tunc.wayfolk.multilanguage.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue

internal expect object LocalAppLocale {
    val current: String @Composable get

    @Composable
    infix fun provides(value: String?): ProvidedValue<*>
}
