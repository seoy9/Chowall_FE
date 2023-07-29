package hong.sy.chowall.recommend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecViewPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 4
    private val fragmentList = listOf(RecommendQ1Fragment(), RecommendQ2Fragment(), RecommendQ3Fragment(), RecommendQ4Fragment())

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}