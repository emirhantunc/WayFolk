package com.tunc.wayfolk.multilanguage.language

import platform.Foundation.NSUserDefaults

internal class IosLanguageStorage : LanguageStorage {

    private val defaults = NSUserDefaults.standardUserDefaults

    override fun read(): String? = defaults.stringForKey(KEY_LANGUAGE)

    override fun write(tag: String?) {
        if (tag == null) defaults.removeObjectForKey(KEY_LANGUAGE) else defaults.setObject(tag, KEY_LANGUAGE)
    }

    private companion object {
        const val KEY_LANGUAGE = "wayfolk_app_language"
    }
}
