package com.tunc.wayfolk.multilanguage.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.staticCompositionLocalOf
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.preferredLanguages

actual object LocalAppLocale {

    private const val APPLE_LANGUAGES_KEY = "AppleLanguages"
    private val systemLocale = NSLocale.preferredLanguages.first() as String
    private val locale = staticCompositionLocalOf { systemLocale }

    private var hasApplied = false
    private var appliedValue: String? = null

    actual val current: String
        @Composable get() = locale.current

    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        if (!hasApplied || appliedValue != value) {
            val defaults = NSUserDefaults.standardUserDefaults
            if (value == null) {
                defaults.removeObjectForKey(APPLE_LANGUAGES_KEY)
            } else {
                defaults.setObject(listOf(value), APPLE_LANGUAGES_KEY)
            }
            hasApplied = true
            appliedValue = value
        }
        return locale provides (value ?: systemLocale)
    }
}
