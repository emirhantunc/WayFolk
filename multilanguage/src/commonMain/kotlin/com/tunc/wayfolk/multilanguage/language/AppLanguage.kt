package com.tunc.wayfolk.multilanguage.language

enum class AppLanguage(val tag: String?, val displayName: WayFolkStringResource) {
    SYSTEM(tag = null, displayName = WayFolkStrings.Language.system),
    ENGLISH(tag = "en", displayName = WayFolkStrings.Language.english),
    TURKISH(tag = "tr", displayName = WayFolkStrings.Language.turkish);

    companion object {
        fun fromTag(tag: String?): AppLanguage = entries.firstOrNull { it.tag == tag } ?: SYSTEM
        fun fromDisplayName(displayName: WayFolkStringResource): AppLanguage =
            entries.firstOrNull { it.displayName == displayName } ?: SYSTEM
    }
}
