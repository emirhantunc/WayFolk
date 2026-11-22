package com.tunc.wayfolk.designsystemkit.theme.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import com.tunc.wayfolk.resources.Res
import com.tunc.wayfolk.resources.ic_bookmark
import com.tunc.wayfolk.resources.ic_heart
import com.tunc.wayfolk.resources.ic_heart_filled
import com.tunc.wayfolk.resources.ic_location_pin
import com.tunc.wayfolk.resources.ic_message
import com.tunc.wayfolk.resources.ic_more_horizontal
import com.tunc.wayfolk.resources.ic_send
import com.tunc.wayfolk.resources.ic_feed
import com.tunc.wayfolk.resources.ic_map
import com.tunc.wayfolk.resources.ic_profile
import com.tunc.wayfolk.resources.ic_rooms
import com.tunc.wayfolk.resources.ic_search
import org.jetbrains.compose.resources.vectorResource

@Immutable
class AppIcons {
    val feed: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_feed)

    val map: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_map)

    val rooms: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_rooms)

    val search: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_search)

    val profile: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_profile)

    val heart: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_heart)

    val heartFilled: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_heart_filled)

    val message: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_message)

    val send: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_send)

    val bookmark: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_bookmark)

    val locationPin: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_location_pin)

    val moreHorizontal: ImageVector
        @Composable
        get() = vectorResource(Res.drawable.ic_more_horizontal)
}

internal val AppLocalIcons = staticCompositionLocalOf { AppIcons() }
