package academy.compose.music.ui

import academy.compose.music.Tags
import academy.compose.music.model.MusicCatalogEvent
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.ui.player.MusicPlayer
import academy.compose.music.ui.search.SearchBar
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun Dashboard(
    state: MusicDashboardState,
    handleEvent: (contentEvent: MusicCatalogEvent) -> Unit
) {
    LaunchedEffect(Unit) {
        handleEvent(MusicCatalogEvent.RefreshContent)
    }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        sheetPeekHeight = 125.dp,
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        scaffoldState = scaffoldState,
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(Tags.TAG_SEARCH_BAR),
                query = state.searchTerm,
                handleQuery = {
                    handleEvent(MusicCatalogEvent.Search(it))
                },
                clearQuery = {
                    handleEvent(MusicCatalogEvent.ClearSearchQuery)
                }
            )
        },
        content = {
            TracksDashboard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
                    .testTag(Tags.TAG_DASHBOARD),
                state = state,
                onTrackClicked = { track ->
                    handleEvent(MusicCatalogEvent.PlayTrack(track))
                }
            )
        },
        sheetContent = {
            MusicPlayer(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState,
                state = state,
                handleEvent = handleEvent
            )
        },
        contentColor = Color.Unspecified
    )
}

@ExperimentalAnimationApi
@Preview
@Composable
fun Preview_MusicPlayer() {
    MaterialTheme {
        Dashboard(
            state = MusicDashboardState(),
            handleEvent = { }
        )
    }
}