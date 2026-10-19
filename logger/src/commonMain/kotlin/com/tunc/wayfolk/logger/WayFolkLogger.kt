package com.tunc.wayfolk.logger

interface WayFolkLogger{

    fun v(message: String?, tag: String? = null)

    fun d(message: String?, tag: String? = null)

    fun i(message: String?, tag: String? = null)

    fun i(e: Throwable?, tag: String? = null)

    fun e(message: String?, tag: String? = null)

    fun e(e: Throwable?, tag: String? = null)

    fun wtf(message: String?, tag: String? = null)

    companion object : WayFolkLogger {

        override fun v(message: String?, tag: String?) {
            platformLog(LogLevel.VERBOSE, tag, message.orEmpty())
        }

        override fun d(message: String?, tag: String?) {
            platformLog(LogLevel.DEBUG, tag, message.orEmpty())
        }

        override fun i(message: String?, tag: String?) {
            platformLog(LogLevel.INFO, tag, message.orEmpty())
        }

        override fun i(e: Throwable?, tag: String?) {
            platformLog(LogLevel.INFO, tag, e?.message.orEmpty())
        }

        override fun e(message: String?, tag: String?) {
            platformLog(LogLevel.ERROR, tag, message.orEmpty())
        }

        override fun e(e: Throwable?, tag: String?) {
            platformLog(LogLevel.ERROR, tag, e?.message.orEmpty())
        }

        override fun wtf(message: String?, tag: String?) {
            platformLog(LogLevel.ASSERT, tag, message.orEmpty())
        }
    }
}