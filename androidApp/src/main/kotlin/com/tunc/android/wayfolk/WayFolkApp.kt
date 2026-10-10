package com.tunc.android.wayfolk

import android.app.Application
import com.tunc.android.wayfolk.di.initKoin
import org.koin.android.ext.koin.androidContext

class WayFolkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WayFolkApp)
        }
    }
}
