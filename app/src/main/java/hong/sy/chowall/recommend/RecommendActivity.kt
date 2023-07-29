package hong.sy.chowall.recommend

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import hong.sy.chowall.HideSoftKey
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityRecommendBinding

class RecommendActivity : HideSoftKey() {
    private lateinit var binding: ActivityRecommendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recQViewPager.adapter = ViewPagerAdapter(this)
        binding.recQViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.recIndicator.attachTo(binding.recQViewPager)

        binding.recQViewPager.run {
            isUserInputEnabled = false
        }

        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarRec
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                when(binding.recQViewPager.currentItem) {
                    0 -> {
                        finish()
                        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
                    }
                    1 -> {
                        binding.recQViewPager.currentItem = 0
                    }
                    2 -> {
                        binding.recQViewPager.currentItem = 1
                    }
                    3 -> {
                        binding.recQViewPager.currentItem = 2
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun changeFragment(index: Int) {
        when(index) {
            1 -> {
                binding.recQViewPager.currentItem = 1
            }
            2 -> {
                binding.recQViewPager.currentItem = 2
            }
            3 -> {
                binding.recQViewPager.currentItem = 3
            }
            4 -> {
                val intent_loading = Intent(this, Recommend_Loading::class.java)
                startActivity(intent_loading)
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            }
        }
    }
}