package yc.dev.tikshorts.ui.screen.xmlscreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class VideoViewerAdapter(
    parentFragment: Fragment,
    private val links: List<String>,
) : FragmentStateAdapter(parentFragment) {
    override fun getItemCount() = links.size

    override fun createFragment(position: Int) = VideoViewerFragment.newInstance(links[position])

}