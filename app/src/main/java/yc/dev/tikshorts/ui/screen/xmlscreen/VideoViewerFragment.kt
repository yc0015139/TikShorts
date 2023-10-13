package yc.dev.tikshorts.ui.screen.xmlscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentVideoViewerBinding

class VideoViewerFragment: Fragment(R.layout.fragment_video_viewer) {

}

@Composable
fun VideoViewerFragmentScreen() {
    AndroidViewBinding(FragmentVideoViewerBinding::inflate)
}