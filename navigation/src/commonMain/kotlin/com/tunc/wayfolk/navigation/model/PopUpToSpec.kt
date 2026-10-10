package com.tunc.wayfolk.navigation.model

import com.tunc.wayfolk.navigation.model.router.ComposeRouter

data class PopUpToSpec(
    val target: ComposeRouter,
    val inclusive: Boolean
)
