package yc.dev.tikshorts.ui.screen.composescreen

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.material.math.MathUtils.lerp
import dagger.hilt.android.EntryPointAccessors
import yc.dev.tikshorts.di.PlayerEntryPoint
import yc.dev.tikshorts.utils.player.PlayerManager
import yc.dev.tikshorts.viewmodel.VideoContainerViewModel
import kotlin.math.absoluteValue

@Composable
fun VideoContainerComposeScreen(
    viewModel: VideoContainerViewModel = hiltViewModel(),
) {
    val links = viewModel.getVideoLinks()
    Content(links)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(links: List<String>) {
    // FIXME: Find the better ways to release the player resource in Compose
    val playerManagers = mutableMapOf<Int, PlayerManager>()
    val pagerState = rememberPagerState { links.size }

    VerticalPager(state = pagerState) { page ->
        Card(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    val pageOffset = (
                        (pagerState.currentPage - page) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue
                    alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                }
        ) {
            val link = links[page]
            val context = LocalContext.current
            val playerManager = playerManagers.getOrPut(page) {
                EntryPointAccessors.fromActivity(
                    context as Activity,
                    PlayerEntryPoint::class.java
                ).getPlayerManager()
            }

            VideoViewerComposeScreen(
                link,
                playerManager,
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            playerManagers.values.forEach { it.release() }
        }
    }
}