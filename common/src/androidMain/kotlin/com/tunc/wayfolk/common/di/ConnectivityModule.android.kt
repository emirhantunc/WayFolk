package com.tunc.wayfolk.common.di

import com.tunc.wayfolk.common.connectivity.AndroidConnectivityObserver
import com.tunc.wayfolk.common.connectivity.ConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val connectivityModule: Module = module {
    single<ConnectivityObserver> { AndroidConnectivityObserver(androidContext()) }
}
