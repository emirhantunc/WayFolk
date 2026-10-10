package com.tunc.wayfolk.contract.network

import kotlinx.serialization.json.JsonElement

interface INetworkError {
    var message: String?
    var data: JsonElement?
    var code: Int?
    var externalCode: String?
}