package com.tunc.wayfolk.contract.model

data class CoreToastModel(
    val message: String,
    val description: String? = null,
    val primaryButtonText: String? = null,
    val secondaryButtonText: String? = null,
    val showCloseButton: Boolean = false,
    val onPrimaryButtonClick: (() -> Unit)? = null,
    val onSecondaryButtonClick: (() -> Unit)? = null,
    val type: CoreToastType,
    val duration: CoreToastDuration = CoreToastDuration.Short
)

enum class CoreToastType {
    Success,
    Error,
    Info,
    Warning
}

enum class CoreToastDuration {
    Short,
    Long,
    Indefinite
}
