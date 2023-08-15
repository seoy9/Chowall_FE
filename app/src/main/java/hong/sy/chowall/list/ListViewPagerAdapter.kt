package hong.sy.chowall.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ListViewPagerAdapter (fa: FragmentActivity, chunDatas: ArrayList<ListData>, gangDatas: ArrayList<ListData>, jeonDatas: ArrayList<ListData>) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 3
    private val fragmentList = listOf(ListChunFragment(chunDatas), ListGangFragment(gangDatas), ListJeonFragment(jeonDatas))

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}