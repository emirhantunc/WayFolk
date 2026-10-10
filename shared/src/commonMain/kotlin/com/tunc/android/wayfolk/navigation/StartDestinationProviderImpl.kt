package com.tunc.android.wayfolk.navigation

import com.tunc.wayfolk.navigation.StartDestinationProvider
import com.tunc.wayfolk.navigation.model.router.ComposeRouter

internal class StartDestinationProviderImpl(
    override val splashDestination: ComposeRouter,
    override val homeDestination: ComposeRouter
) : StartDestinationProvider
