package com.tunc.wayfolk.core.extension

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

fun <T> Flow<T>.flowOnIO(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<T> =
    flowOn(dispatcher)

suspend fun <T> withIO(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    block: suspend CoroutineScope.() -> T,
): T = withContext(dispatcher, block)
