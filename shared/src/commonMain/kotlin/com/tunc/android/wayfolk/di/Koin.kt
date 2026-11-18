package com.tunc.android.wayfolk.di

import com.tunc.wayfolk.common.di.commonModule
import com.tunc.wayfolk.core.presentation.di.corePresentationModule
import com.tunc.wayfolk.feature.di.baseFeatureModules
import com.tunc.wayfolk.feature.home.presentation.di.homePresentationModule
import com.tunc.wayfolk.multilanguage.di.multiLanguageModule
import com.tunc.wayfolk.splash.presentation.di.splashPresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            commonModule,
            multiLanguageModule,
            corePresentationModule,
            baseFeatureModules,
            homePresentationModule,
            navigationModule,
            appModule,
            splashPresentationModule
        )
    }
}
