package com.tunc.wayfolk.multilanguage.language

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface LanguageManager {
    val language: StateFlow<AppLanguage>
    fun setLanguage(language: AppLanguage)
}

internal class LanguageManagerImpl(
    private val storage: LanguageStorage,
) : LanguageManager {

    private val _language = MutableStateFlow(AppLanguage.fromTag(storage.read()))
    override val language: StateFlow<AppLanguage> = _language.asStateFlow()

    override fun setLanguage(language: AppLanguage) {
        storage.write(language.tag)
        _language.value = language
    }
}
