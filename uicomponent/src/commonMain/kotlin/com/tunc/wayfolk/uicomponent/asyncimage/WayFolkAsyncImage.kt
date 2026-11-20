package com.tunc.wayfolk.uicomponent.asyncimage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun WayFolkAsyncImage(
    url: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    placeholder: Painter? = null,
    error: Painter? = placeholder,
    crossfadeMillis: Int = 200,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
) {
    val context = LocalPlatformContext.current
    val request = remember(context, url, crossfadeMillis) {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(crossfadeMillis)
            .build()
    }
    AsyncImage(
        model = request,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment,
        placeholder = placeholder,
        error = error,
        fallback = error,
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = onError,
    )
}