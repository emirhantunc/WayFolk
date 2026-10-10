package com.tunc.wayfolk.navigation.model.router

import androidx.navigation3.runtime.NavKey


interface ComposeRouter : NavKey {
    val screenName: String
}
