package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentContainerBinding
import yc.dev.tikshorts.databinding.FragmentVideoViewerBinding

class VideoViewerFragment : Fragment(R.layout.fragment_video_viewer) {
    private val binding by viewBinding(FragmentVideoViewerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvText.text = "This is VideoViewerFragment"
    }
}

@Composable
fun VideoViewerFragmentScreen() {
    AndroidViewBinding(FragmentContainerBinding::inflate)
}