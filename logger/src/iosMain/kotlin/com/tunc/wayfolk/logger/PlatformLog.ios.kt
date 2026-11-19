package com.tunc.wayfolk.logger

import platform.Foundation.NSLog

internal actual fun platformLog(level: LogLevel, tag: String?, message: String) {
    NSLog("%@", "${level.name}/${tag ?: "WayFolk"}: $message")
}
