package hong.sy.chowall.recommend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 4

    override fun getItemCount(): Int = NUM_PAGES // 페이지 수 리턴

    override fun createFragment(position: Int): Fragment {
        return when(position){ // 페이지 포지션에 따라 그에 맞는 프래그먼트를 보여줌
            0 -> RecommendQ1Fragment()
            1 -> RecommendQ2Fragment()
            2 -> RecommendQ3Fragment()
            else -> RecommendQ4Fragment()
        }
    }
}