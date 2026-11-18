package com.tunc.wayfolk.multilanguage.di

import com.tunc.wayfolk.multilanguage.language.LanguageManager
import com.tunc.wayfolk.multilanguage.language.LanguageManagerImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal expect val languageStorageModule: Module

val multiLanguageModule
    get() = module {
        includes(languageStorageModule)
        singleOf(::LanguageManagerImpl) { bind<LanguageManager>() }
    }
