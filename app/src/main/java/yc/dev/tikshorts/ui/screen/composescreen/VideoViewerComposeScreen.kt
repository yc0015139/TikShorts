package yc.dev.tikshorts.ui.screen.composescreen

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.EntryPointAccessors
import yc.dev.tikshorts.di.PlayerModule
import yc.dev.tikshorts.di.ViewModelFactoryProvider
import yc.dev.tikshorts.utils.compose.composeSimulatedLifecycle
import yc.dev.tikshorts.utils.player.PlayerManager
import yc.dev.tikshorts.viewmodel.VideoViewerViewModel

@Composable
fun VideoViewerComposeScreen(
    link: String,
) {
    val simulatedLifecycle = composeSimulatedLifecycle()
    val context = LocalContext.current
    val viewModel = videoViewerViewModel(
        context = context,
        playerManager = PlayerModule.providePlayerManager(
            context = context,
            lifecycle = simulatedLifecycle,
        )
    )
}

@Composable
private fun videoViewerViewModel(
    context: Context,
    playerManager: PlayerManager
): VideoViewerViewModel {
    val factory = EntryPointAccessors.fromActivity(
        context as Activity,
        ViewModelFactoryProvider::class.java
    ).videoViewerViewModelFactory()
    return viewModel(
        factory = VideoViewerViewModel.provideVideoViewerViewModelFactory(
            factory,
            playerManager,
        )
    )
}

@Composable
private fun Content(
    link: String,
    simulatedLifecycle: Lifecycle,
) {

}