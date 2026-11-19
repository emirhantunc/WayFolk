package com.tunc.wayfolk.feature.home.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tunc.wayfolk.multilanguage.Strings
import com.tunc.wayfolk.multilanguage.home_story_image_description
import com.tunc.wayfolk.uicomponent.asyncimage.WayFolkAsyncImage
import org.jetbrains.compose.resources.stringResource


@Composable
fun WayFolkStory(
    url : String
) {
    WayFolkAsyncImage(
        url = url,
        contentDescription = stringResource(Strings.string.home_story_image_description),
        modifier = Modifier
    )


}
