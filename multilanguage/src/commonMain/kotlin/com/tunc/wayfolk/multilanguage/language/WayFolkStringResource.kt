package com.tunc.wayfolk.multilanguage.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getPluralString as composeGetPluralString
import org.jetbrains.compose.resources.getString as composeGetString
import org.jetbrains.compose.resources.pluralStringResource as composePluralStringResource
import org.jetbrains.compose.resources.stringResource as composeStringResource

@Immutable
class WayFolkStringResource internal constructor(
    internal val resource: StringResource,
) {
    val key: String get() = resource.key

    override fun equals(other: Any?): Boolean =
        other is WayFolkStringResource && other.resource == resource

    override fun hashCode(): Int = resource.hashCode()

    override fun toString(): String = "WayFolkStringResource($key)"
}

@Immutable
class WayFolkPluralResource internal constructor(
    internal val resource: PluralStringResource,
) {
    val key: String get() = resource.key

    override fun equals(other: Any?): Boolean =
        other is WayFolkPluralResource && other.resource == resource

    override fun hashCode(): Int = resource.hashCode()

    override fun toString(): String = "WayFolkPluralResource($key)"
}

@Composable
fun stringResource(resource: WayFolkStringResource, vararg formatArgs: Any): String =
    composeStringResource(resource.resource, *formatArgs)

@Composable
fun pluralStringResource(resource: WayFolkPluralResource, quantity: Int, vararg formatArgs: Any): String =
    composePluralStringResource(resource.resource, quantity, *formatArgs)

suspend fun getString(resource: WayFolkStringResource, vararg formatArgs: Any): String =
    composeGetString(resource.resource, *formatArgs)

suspend fun getPluralString(resource: WayFolkPluralResource, quantity: Int, vararg formatArgs: Any): String =
    composeGetPluralString(resource.resource, quantity, *formatArgs)
