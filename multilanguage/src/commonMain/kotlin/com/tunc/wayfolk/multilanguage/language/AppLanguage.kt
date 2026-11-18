package com.tunc.wayfolk.multilanguage.language

import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.language_english
import com.tunc.wayfolk.multilanguage.language_system
import com.tunc.wayfolk.multilanguage.language_turkish
import org.jetbrains.compose.resources.StringResource

enum class AppLanguage(val tag: String?, val displayName: StringResource) {
    SYSTEM(tag = null, displayName = Strings.string.language_system),
    ENGLISH(tag = "en", displayName = Strings.string.language_english),
    TURKISH(tag = "tr", displayName = Strings.string.language_turkish);

    companion object {
        fun fromTag(tag: String?): AppLanguage = entries.firstOrNull { it.tag == tag } ?: SYSTEM
    }
}
