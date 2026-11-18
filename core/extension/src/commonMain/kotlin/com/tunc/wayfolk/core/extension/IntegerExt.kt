package com.tunc.wayfolk.core.extension

const val MASK = 0xFFFFFF



val Int?.orZero: Int
    get() = this ?: 0