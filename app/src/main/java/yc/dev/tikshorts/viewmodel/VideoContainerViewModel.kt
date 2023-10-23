package yc.dev.tikshorts.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import yc.dev.tikshorts.data.repository.VideoSourceRepository
import javax.inject.Inject

@HiltViewModel
class VideoContainerViewModel @Inject constructor(
    private val videoSourceRepository: VideoSourceRepository
) : ViewModel() {

    fun getVideoLinks(): List<String> = videoSourceRepository.fetchVideoSources()
}