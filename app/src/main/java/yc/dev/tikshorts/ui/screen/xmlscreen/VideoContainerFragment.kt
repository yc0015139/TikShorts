package yc.dev.tikshorts.ui.screen.xmlscreen

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import yc.dev.tikshorts.R
import yc.dev.tikshorts.databinding.FragmentContainerBinding
import yc.dev.tikshorts.databinding.FragmentVideoContainerBinding
import yc.dev.tikshorts.viewmodel.VideoContainerViewModel

@AndroidEntryPoint
class VideoContainerFragment : Fragment(R.layout.fragment_video_container) {
    private val binding by viewBinding(FragmentVideoContainerBinding::bind)
    private val viewModel: VideoContainerViewModel by viewModels()

    private lateinit var adapter: VideoViewerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        val links = viewModel.getVideoLinks()
        adapter = VideoViewerAdapter(this, links)
        binding.vpVideoContainer.adapter = adapter
    }

    companion object {
        fun newInstance() = VideoContainerFragment()
    }
}

@Composable
fun VideoContainerFragmentScreen(fm: FragmentManager) {
    AndroidViewBinding(FragmentContainerBinding::inflate) {
        fm.commit {
            replace(fragmentContainerView.id, VideoContainerFragment.newInstance())
        }
    }
}