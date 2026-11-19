package com.tunc.android.wayfolk

import android.app.Application
import com.tunc.android.wayfolk.di.initKoin
import logcat.AndroidLogcatLogger
import org.koin.android.ext.koin.androidContext

class WayFolkApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this)
        initKoin {
            androidContext(this@WayFolkApp)
        }
    }
}
