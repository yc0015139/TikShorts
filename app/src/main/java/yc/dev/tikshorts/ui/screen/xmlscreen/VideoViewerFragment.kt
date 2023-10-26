package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentVideoViewerBinding
import yc.dev.tikshorts.utils.player.PlayerManager
import yc.dev.tikshorts.viewmodel.ARG_VIDEO_LINK
import yc.dev.tikshorts.viewmodel.VideoViewerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class VideoViewerFragment : Fragment(R.layout.fragment_video_viewer) {
    private val binding by viewBinding(FragmentVideoViewerBinding::bind)

    @Inject
    lateinit var playerManager: PlayerManager

    @Inject
    lateinit var viewModelFactory: VideoViewerViewModel.Factory
    private val viewModel: VideoViewerViewModel by viewModels(
        factoryProducer = {
            VideoViewerViewModel.provideVideoViewerViewModelFactory(
                viewModelFactory,
                playerManager,
            )
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareVideo()
        observePlayer()
    }

    private fun prepareVideo() {
        val link = arguments?.getString(ARG_VIDEO_LINK)
        link?.also { viewModel.preparePlayerByLink(it) }
    }

    private fun observePlayer() {
        viewModel.player.observe(viewLifecycleOwner) {
            binding.pvVideo.player = it
        }
    }

    companion object {

        fun newInstance(link: String): VideoViewerFragment = VideoViewerFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_VIDEO_LINK, link)
            }
        }
    }
}