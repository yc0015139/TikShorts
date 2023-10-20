package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
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

    companion object {
        fun newInstance() = VideoViewerFragment()
    }
}

@Composable
fun VideoViewerFragmentScreen(fm: FragmentManager) {
    AndroidViewBinding(FragmentContainerBinding::inflate) {
        fm.commit {
            replace(fragmentContainerView.id, VideoViewerFragment.newInstance())
        }
    }
}