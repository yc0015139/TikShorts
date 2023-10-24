package yc.dev.tikshorts.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yc.dev.tikshorts.utils.player.PlayerManager
import javax.inject.Inject

const val ARG_VIDEO_LINK = "VIDEO_LINK"

@HiltViewModel
class VideoViewerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val playerManager: PlayerManager,
) : ViewModel() {

    fun getUrl() = savedStateHandle.get<String>(ARG_VIDEO_LINK)
}