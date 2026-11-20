package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.home.presentation.component.HomeStoryRow
import com.tunc.wayfolk.feature.home.presentation.model.StoryImageState
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val onStoryImageStateChange = remember(viewModel) {
        { storyId: String, imageState: StoryImageState ->
            viewModel.onAction(
                HomeScreenContract.Action.OnStoryImageStateChange(
                    storyId = storyId,
                    imageState = imageState,
                )
            )
        }
    }

    BaseScreen(
        screenName = HomeConstants.Home.NAME,
        viewModel = viewModel,
        content = {
            HomeStoryRow(
                stories = state.stories,
                onStoryImageStateChange = onStoryImageStateChange,
            )
        }
    )
}
