package com.tunc.wayfolk.common.di

import com.tunc.wayfolk.common.BottomNavigationStateManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule
    get() = module {
        single<CoroutineDispatcher>(named<com.tunc.wayfolk.contract.qualifiers.Dispatchers.IO>()) { Dispatchers.IO }
        singleOf(::BottomNavigationStateManager)
    }
