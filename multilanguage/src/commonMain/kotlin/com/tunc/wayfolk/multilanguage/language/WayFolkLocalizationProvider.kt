package com.tunc.wayfolk.multilanguage.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject

val LocalAppLanguage = staticCompositionLocalOf { AppLanguage.SYSTEM }

@Composable
fun WayFolkLocalizationProvider(
    languageManager: LanguageManager = koinInject(),
    content: @Composable () -> Unit,
) {
    val language by languageManager.language.collectAsStateWithLifecycle()
    CompositionLocalProvider(
        LocalAppLocale provides language.tag,
        LocalAppLanguage provides language,
    ) {
        key(language) {
            content()
        }
    }
}
