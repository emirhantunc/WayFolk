package com.tunc.android.jvm

const val MASK = 0xFFFFFF



val Int?.orZero: Int
    get() = this ?: 0