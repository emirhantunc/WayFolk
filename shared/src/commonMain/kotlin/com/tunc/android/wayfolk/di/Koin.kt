package com.tunc.android.wayfolk.di

import com.tunc.wayfolk.core.presentation.di.corePresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            corePresentationModule
        )
    }
}
