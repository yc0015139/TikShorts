package yc.dev.tikshorts.utils.player

import android.net.Uri
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PlayerManager constructor(
    private val player: ExoPlayer,
    lifecycle: Lifecycle? = null,
    private val mainDispatcher: CoroutineDispatcher,
    private val defaultDispatcher: CoroutineDispatcher,
) {
    init {
        lifecycle?.run {
            val observer: DefaultLifecycleObserver = object : DefaultLifecycleObserver {

                override fun onResume(owner: LifecycleOwner) {
                    player.seekTo(0)
                    player.prepare()
                }

                override fun onPause(owner: LifecycleOwner) {
                    player.stop()
                }

                override fun onDestroy(owner: LifecycleOwner) {
                    player.stop()
                    player.release()
                    owner.lifecycle.removeObserver(this)
                }
            }
            addObserver(observer)
        }
        // TODO: Implement compose state observer?
    }

    suspend fun requirePlayerByLink(link: String) = flow {
        player.apply {
            val mediaItem = MediaItem.fromUri(Uri.parse(link))
            withContext(mainDispatcher) {
                setMediaItem(mediaItem)
                repeatMode = Player.REPEAT_MODE_ONE
                playWhenReady = true
            }
            emit(this@apply)
        }
    }.flowOn(defaultDispatcher)

}
