package yc.dev.tikshorts.utils.player

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.media3.exoplayer.ExoPlayer
import kotlin.coroutines.CoroutineContext

class PlayerManager constructor(
    private val player: ExoPlayer,
    lifecycle: Lifecycle,
    private val mainDispatcher: CoroutineContext,
) {
    private val observer: DefaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onStart(owner: LifecycleOwner) {
            play()
        }

        override fun onPause(owner: LifecycleOwner) {
            pause()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            player.release()
            owner.lifecycle.removeObserver(this)
        }
    }

    fun observeInLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(observer)
    }


    fun setVideoLink(link: String) {
        player.setVideoSurface()
    }

    fun play() {
        player.play()
    }

    fun pause() {
        player.stop()
    }
}
