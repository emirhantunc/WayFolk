package com.tunc.wayfolk.multilanguage.text

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {

    data class Raw(val value: String) : UiText

    class Resource(val res: StringResource, vararg val args: Any) : UiText

    class Plural(val res: PluralStringResource, val quantity: Int, vararg val args: Any) : UiText
}

@Composable
fun UiText.asString(): String = when (this) {
    is UiText.Raw -> value
    is UiText.Resource -> stringResource(res, *args)
    is UiText.Plural -> pluralStringResource(res, quantity, *args)
}

suspend fun UiText.resolve(): String = when (this) {
    is UiText.Raw -> value
    is UiText.Resource -> getString(res, *args)
    is UiText.Plural -> getPluralString(res, quantity, *args)
}
