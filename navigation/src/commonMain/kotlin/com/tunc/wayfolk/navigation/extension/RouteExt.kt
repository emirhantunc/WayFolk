package com.tunc.wayfolk.navigation.extension

import com.tunc.wayfolk.navigation.model.router.ComposeRouter


val ComposeRouter.route: String
    get() = this::class.qualifiedName.orEmpty()