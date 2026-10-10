package com.tunc.wayfolk.feature.home.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp


private const val INTERACTION_ENABLED_DELAY = 2000L
private const val SHIMMER_STATUS_CHANGE_DURATION = 350

//
//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun BaseScreen(
//    modifier: Modifier = Modifier,
//    viewModel: ViewModelBehaviour,
//    showBottomAppBar: Boolean = false,
//    showStatusBar: Boolean = true,
//    enableContentLoading: Boolean = true,
//    contentLoadingPaddings: PaddingValues = PaddingValues(vertical = 4.dp),
//    customScreenPaddings: PaddingValues = PaddingValues(),
//    containerColor: AppColor = AppThemeProvider.colors.primaryContainer,
//    useCustomScreenPaddings: Boolean = false,
//    shimmerResourceId: Int? = null,
//    shimmerContentScale: ContentScale = ContentScale.Crop,
//    shimmerStatusDuration: Int = com.tunc.android.feature.base.SHIMMER_STATUS_CHANGE_DURATION,
//    shimmerOverlayContent: @Composable ((Boolean) -> Unit)? = null,
//    enableUserInteractions: Boolean = true,
//    toolbar: @Composable (() -> Unit)? = null,
//    content: @Composable ColumnScope.() -> Unit,
//    screenName: String = String.Empty
//) {
//
//}