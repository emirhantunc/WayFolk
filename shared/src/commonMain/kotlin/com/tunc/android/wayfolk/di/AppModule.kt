package com.tunc.android.wayfolk.di

import com.tunc.android.wayfolk.MainViewModel
import com.tunc.android.wayfolk.navigation.ComposeNavManagerImpl
import com.tunc.wayfolk.contract.qualifiers.Dispatchers
import com.tunc.wayfolk.navigation.model.ComposeNavManager
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val appModule = module {
    single<ComposeNavManager> {
        ComposeNavManagerImpl(dispatcher = get(named<Dispatchers.IO>()))
    }
    viewModelOf(::MainViewModel)
}
