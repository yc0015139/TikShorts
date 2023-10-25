package yc.dev.tikshorts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import yc.dev.tikshorts.utils.player.PlayerManager

const val ARG_VIDEO_LINK = "VIDEO_LINK"

class VideoViewerViewModel @AssistedInject constructor(
    @Assisted private val playerManager: PlayerManager,
) : ViewModel() {

    fun getUrl() = savedStateHandle.get<String>(ARG_VIDEO_LINK)

    @AssistedFactory
    interface Factory {
        fun create(
            playerManager: PlayerManager,
        ): VideoViewerViewModel
    }

    companion object {
        fun provideVideoViewerViewModelFactory(
            factory: Factory,
            playerManager: PlayerManager,
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(
                        playerManager,
                    ) as T
                }
            }
        }
    }
}