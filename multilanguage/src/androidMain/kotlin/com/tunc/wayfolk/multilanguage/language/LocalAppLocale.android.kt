package com.tunc.wayfolk.multilanguage.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

actual object LocalAppLocale {

    private var systemLocale: Locale? = null

    actual val current: String
        @Composable get() = Locale.getDefault().toLanguageTag()

    @Suppress("DEPRECATION")
    @Composable
    actual infix fun provides(value: String?): ProvidedValue<*> {
        val configuration = LocalConfiguration.current
        val resources = LocalContext.current.resources
        val default = systemLocale ?: Locale.getDefault().also { systemLocale = it }
        val locale = remember(value, default) { value?.let(Locale::forLanguageTag) ?: default }
        if (Locale.getDefault() != locale || configuration.locales[0] != locale) {
            Locale.setDefault(locale)
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
        return LocalConfiguration provides configuration
    }
}
