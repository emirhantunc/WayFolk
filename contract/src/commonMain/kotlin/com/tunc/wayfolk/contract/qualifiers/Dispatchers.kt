package com.tunc.wayfolk.contract.qualifiers

import org.koin.core.annotation.Named

object Dispatchers {
    @Named
    annotation class Main

    @Named
    annotation class IO

    @Named
    annotation class Default

    @Named
    annotation class Unconfined
}
