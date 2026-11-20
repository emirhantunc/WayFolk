package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.designsystemkit.component.WayFolkText
import com.tunc.wayfolk.multilanguage.language.WayFolkStrings
import com.tunc.wayfolk.multilanguage.language.stringResource
import com.tunc.wayfolk.uicomponent.asyncimage.WayFolkAsyncImage


@Composable
fun WayFolkStory(
    url : String
) {
    WayFolkAsyncImage(
        url = url,
        contentDescription = stringResource(WayFolkStrings.Home.storyImageDescription),
        modifier = Modifier
    )

    WayFolkText(text = WayFolkStrings.Home.storyImageDescription)
}
