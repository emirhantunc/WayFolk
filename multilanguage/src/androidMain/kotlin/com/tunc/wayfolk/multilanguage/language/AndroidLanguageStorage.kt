package com.tunc.wayfolk.multilanguage.language

import android.content.Context
import androidx.core.content.edit

internal class AndroidLanguageStorage(context: Context) : LanguageStorage {

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun read(): String? = preferences.getString(KEY_LANGUAGE, null)

    override fun write(tag: String?) {
        preferences.edit { putString(KEY_LANGUAGE, tag) }
    }

    private companion object {
        const val PREFERENCES_NAME = "wayfolk_language"
        const val KEY_LANGUAGE = "app_language"
    }
}
