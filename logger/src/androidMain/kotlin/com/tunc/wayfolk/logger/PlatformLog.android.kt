package com.tunc.wayfolk.logger

import logcat.LogPriority
import logcat.logcat

internal actual fun platformLog(level: LogLevel, tag: String?, message: String) {
    val priority = when (level) {
        LogLevel.VERBOSE -> LogPriority.VERBOSE
        LogLevel.DEBUG -> LogPriority.DEBUG
        LogLevel.INFO -> LogPriority.INFO
        LogLevel.WARN -> LogPriority.WARN
        LogLevel.ERROR -> LogPriority.ERROR
        LogLevel.ASSERT -> LogPriority.ASSERT
    }
    logcat(tag ?: "WayFolk", priority) { message }
}
