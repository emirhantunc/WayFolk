package com.tunc.wayfolk.core.network.di

import com.tunc.wayfolk.core.network.ApiHandler
import com.tunc.wayfolk.core.network.HttpClientFactory
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule
    get() = module {
        single {
            Json {
                ignoreUnknownKeys = true
                explicitNulls = false
                isLenient = true
            }
        }
        single<HttpClient> { HttpClientFactory.create(config = get(), json = get()) }
        single { ApiHandler(json = get()) }
    }
