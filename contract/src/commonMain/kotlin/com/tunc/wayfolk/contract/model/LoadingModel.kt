package com.tunc.wayfolk.contract.model

import com.tunc.wayfolk.contract.network.LoadingType

data class LoadingModel(
    val show: Boolean,
    val loadingType: LoadingType
)