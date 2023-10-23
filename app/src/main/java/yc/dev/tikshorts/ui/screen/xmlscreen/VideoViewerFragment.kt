package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentVideoViewerBinding
import yc.dev.tikshorts.viewmodel.ARG_VIDEO_LINK
import yc.dev.tikshorts.viewmodel.VideoViewerViewModel

@AndroidEntryPoint
class VideoViewerFragment : Fragment(R.layout.fragment_video_viewer) {
    private val binding by viewBinding(FragmentVideoViewerBinding::bind)
    private val viewModel: VideoViewerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvText.text = viewModel.getUrl()
    }

    companion object {

        fun newInstance(link: String): VideoViewerFragment = VideoViewerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_LINK, link)
            }
        }
    }
}