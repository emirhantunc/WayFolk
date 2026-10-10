package com.tunc.wayfolk.navigation

import com.tunc.wayfolk.navigation.model.router.ComposeRouter

interface StartDestinationProvider {
    val splashDestination: ComposeRouter
    val homeDestination: ComposeRouter
}