package com.tunc.wayfolk.logger

internal enum class LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT
}

internal expect fun platformLog(level: LogLevel, tag: String?, message: String)
