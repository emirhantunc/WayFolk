package com.tunc.wayfolk.feature.home.presentation.util

private const val GROUP_SIZE = 3

internal fun Int.formatWithGrouping(separator: String): String {
    val digits = kotlin.math.abs(toLong()).toString()
    val grouped = digits.reversed().chunked(GROUP_SIZE).joinToString(separator).reversed()
    return if (this < 0) "-$grouped" else grouped
}
