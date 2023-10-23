package yc.dev.tikshorts.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val ARG_VIDEO_LINK = "VIDEO_LINK"

@HiltViewModel
class VideoViewerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {

    fun getUrl() = savedStateHandle.get<String>(ARG_VIDEO_LINK)
}