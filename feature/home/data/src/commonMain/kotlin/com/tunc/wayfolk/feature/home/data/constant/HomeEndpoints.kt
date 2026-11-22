package com.tunc.wayfolk.feature.home.data.constant

internal object HomeEndpoints {
    const val FEED = "get/home/feed"
    const val UNREAD_MESSAGES_COUNT = "get/home/unread-messages-count"

    object QueryParam {
        const val CURSOR = "cursor"
        const val LIMIT = "limit"
    }
}
