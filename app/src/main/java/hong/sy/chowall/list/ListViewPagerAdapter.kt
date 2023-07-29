package hong.sy.chowall.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ListViewPagerAdapter  (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 3
    private val fragmentList = listOf(ListChunFragment(), ListGangFragment(), ListJeonFragment())

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}