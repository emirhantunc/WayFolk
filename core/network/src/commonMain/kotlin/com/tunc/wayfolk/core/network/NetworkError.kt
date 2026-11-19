package com.tunc.wayfolk.core.network

import com.tunc.wayfolk.contract.network.INetworkError
import kotlinx.serialization.json.JsonElement

data class NetworkError(
    override var message: String? = null,
    override var data: JsonElement? = null,
    override var code: Int? = null,
    override var externalCode: String? = null,
) : INetworkError
