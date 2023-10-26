package yc.dev.tikshorts.ui.screen.composescreen

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import dagger.hilt.android.EntryPointAccessors
import yc.dev.tikshorts.di.ViewModelFactoryProvider
import yc.dev.tikshorts.utils.player.PlayerManager
import yc.dev.tikshorts.viewmodel.VideoViewerViewModel

@Composable
fun VideoViewerComposeScreen(
    link: String,
    playerManager: PlayerManager,
    viewModel: VideoViewerViewModel = videoViewerViewModel(
        key = link,
        playerManager = playerManager,
    ),
) {
    val player by viewModel.player.observeAsState()
    LaunchedEffect(link) {
        viewModel.preparePlayerByLink(link)
    }

    player?.let { Content(it) }
}

@Composable
private fun videoViewerViewModel(
    key: String,
    playerManager: PlayerManager,
): VideoViewerViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).videoViewerViewModelFactory()
    return viewModel(
        factory = VideoViewerViewModel.provideVideoViewerViewModelFactory(
            factory,
            playerManager,
        ),
        key = key
    )
}

@Composable
private fun Content(player: ExoPlayer) {
    // TODO: PlayerManager should control the player event
    // FIXME: DO NOT play the video on multiple Composes
    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = { context ->
            PlayerView(context).also {
                player.prepare()
                it.useController = false
                it.player = player
            }
        },
        update = {
            it.player = player
        }
    )
    DisposableEffect(Unit) {
        onDispose {
            player.stop()
        }
    }
}
