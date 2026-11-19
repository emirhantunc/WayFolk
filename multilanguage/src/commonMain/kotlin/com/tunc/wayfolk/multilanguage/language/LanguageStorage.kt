package com.tunc.wayfolk.multilanguage.language

internal interface LanguageStorage {
    fun read(): String?
    fun write(tag: String?)
}
