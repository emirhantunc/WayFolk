package com.tunc.wayfolk.multilanguage.di

import com.tunc.wayfolk.multilanguage.language.AndroidLanguageStorage
import com.tunc.wayfolk.multilanguage.language.LanguageStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val languageStorageModule: Module = module {
    single<LanguageStorage> { AndroidLanguageStorage(androidContext()) }
}
