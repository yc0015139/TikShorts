package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentVideoViewerBinding

class VideoViewerFragment : Fragment(R.layout.fragment_video_viewer) {
    private val binding by viewBinding(FragmentVideoViewerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            binding.tvText.text = getString(ARG_VIDEO_LINK)
        }
    }

    companion object {
        private const val ARG_VIDEO_LINK = "VIDEO_LINK"

        fun newInstance(link: String): VideoViewerFragment = VideoViewerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_LINK, link)
            }
        }
    }
}