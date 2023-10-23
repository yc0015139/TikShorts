package yc.dev.tikshorts.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoSourceRepository @Inject constructor(

) {

    fun fetchVideoSources(): List<String> {
        return listOf("aabbcc", "ccddee", "eeffgg", "gghhii", "iijjkk", "kkllmm", "nnoopp")
    }
}