package com.tunc.wayfolk.feature.home.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tunc.wayfolk.designsystemkit.theme.AppThemeProvider
import com.tunc.wayfolk.feature.base.BaseScreen
import com.tunc.wayfolk.feature.home.presentation.component.HomePostCard
import com.tunc.wayfolk.feature.home.presentation.component.HomeStoryRow
import com.tunc.wayfolk.feature.home.presentation.component.HomeTopBar
import com.tunc.wayfolk.feature.home.presentation.util.HomeConstants
import com.tunc.wayfolk.uicomponent.pagination.WayFolkPagingLazyColumn
import org.koin.compose.viewmodel.koinViewModel

private const val STORIES_KEY = "home_stories"
private const val STORIES_CONTENT_TYPE = "stories"
private const val POST_CONTENT_TYPE = "post"
private val DividerThickness = 1.dp

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onAction = remember(viewModel) { viewModel::onAction }

    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        screenName = HomeConstants.Home.NAME,
        viewModel = viewModel,
        showBottomAppBar = true,
        containerColor = AppThemeProvider.colors.background,
        content = {
            HomeContent(state = state, onAction = onAction)
        }
    )
}

@Composable
internal fun HomeContent(
    state: HomeScreenContract.State,
    onAction: (HomeScreenContract.Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dividerColor = AppThemeProvider.colors.divider.color
    val listState = rememberLazyListState()
    val pagination = state.pagination

    Column(modifier = modifier.fillMaxSize()) {
        HomeTopBar(
            unreadMessagesCount = state.unreadMessagesCount,
            onNotificationsClick = { onAction(HomeScreenContract.Action.OnNotificationsClick) },
            onMessagesClick = { onAction(HomeScreenContract.Action.OnMessagesClick) },
        )
        HorizontalDivider(thickness = DividerThickness, color = dividerColor)

        WayFolkPagingLazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            isLoadingMore = pagination.isLoadingNextPage,
            hasLoadMoreError = pagination.hasNextPageError,
            canLoadMore = state.posts.isNotEmpty() && !pagination.endReached && !pagination.hasError,
            prefetchDistance = HomeConstants.Feed.PREFETCH_DISTANCE,
            onLoadMore = { onAction(HomeScreenContract.Action.OnLoadMore) },
        ) {
            item(key = STORIES_KEY, contentType = STORIES_CONTENT_TYPE) {
                HomeStoryRow(
                    stories = state.stories,
                    onStoryClick = { storyId -> onAction(HomeScreenContract.Action.OnStoryClick(storyId)) },
                    onStoryImageStateChange = { storyId, imageState ->
                        onAction(HomeScreenContract.Action.OnStoryImageStateChange(storyId, imageState))
                    },
                )
                HorizontalDivider(thickness = DividerThickness, color = dividerColor)
            }

            items(
                items = state.posts,
                key = { post -> post.id },
                contentType = { POST_CONTENT_TYPE },
            ) { post ->
                HomePostCard(
                    post = post,
                    onAuthorClick = { onAction(HomeScreenContract.Action.OnPostAuthorClick(post.authorId)) },
                    onMoreClick = { onAction(HomeScreenContract.Action.OnPostMoreClick(post.id)) },
                    onLikeClick = { onAction(HomeScreenContract.Action.OnPostLikeClick(post.id)) },
                    onCommentClick = { onAction(HomeScreenContract.Action.OnPostCommentClick(post.id)) },
                    onShareClick = { onAction(HomeScreenContract.Action.OnPostShareClick(post.id)) },
                    onSaveClick = { onAction(HomeScreenContract.Action.OnPostSaveClick(post.id)) },
                )
                HorizontalDivider(thickness = DividerThickness, color = dividerColor)
            }
        }
    }
}
