package com.tunc.wayfolk.multilanguage.text

import androidx.compose.runtime.Composable
import com.tunc.wayfolk.multilanguage.language.WayFolkPluralResource
import com.tunc.wayfolk.multilanguage.language.WayFolkStringResource
import com.tunc.wayfolk.multilanguage.language.getPluralString
import com.tunc.wayfolk.multilanguage.language.getString
import com.tunc.wayfolk.multilanguage.language.pluralStringResource
import com.tunc.wayfolk.multilanguage.language.stringResource

sealed interface UiText {

    data class Raw(val value: String) : UiText

    class Resource(val res: WayFolkStringResource, vararg val args: Any) : UiText {

        override fun equals(other: Any?): Boolean =
            other is Resource && other.res == res && other.args.contentEquals(args)

        override fun hashCode(): Int = 31 * res.hashCode() + args.contentHashCode()

        override fun toString(): String = "UiText.Resource(${res.key}, ${args.contentToString()})"
    }

    class Plural(val res: WayFolkPluralResource, val quantity: Int, vararg val args: Any) : UiText {

        override fun equals(other: Any?): Boolean =
            other is Plural && other.res == res && other.quantity == quantity && other.args.contentEquals(args)

        override fun hashCode(): Int = 31 * (31 * res.hashCode() + quantity) + args.contentHashCode()

        override fun toString(): String = "UiText.Plural(${res.key}, $quantity, ${args.contentToString()})"
    }
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
