package com.tunc.wayfolk.multilanguage.di

import com.tunc.wayfolk.multilanguage.language.IosLanguageStorage
import com.tunc.wayfolk.multilanguage.language.LanguageStorage
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val languageStorageModule: Module = module {
    single<LanguageStorage> { IosLanguageStorage() }
}
