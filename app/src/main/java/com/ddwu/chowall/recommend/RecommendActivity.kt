package com.ddwu.chowall.recommend

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.ddwu.chowall.HideSoftKey
import com.ddwu.chowall.R
import com.ddwu.chowall.databinding.ActivityRecommendBinding

class RecommendActivity : HideSoftKey() {
    private lateinit var binding: ActivityRecommendBinding
    private var city = ""
    private var companion = 0
    private var days = 0
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recQViewPager.adapter = RecViewPagerAdapter(this)
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

    fun changeFragment(index: Int, data : Any) {
        when(index) {
            1 -> {
                binding.recQViewPager.currentItem = 1
                city = data.toString()
            }
            2 -> {
                binding.recQViewPager.currentItem = 2
                companion = data.toString().toInt()
            }
            3 -> {
                binding.recQViewPager.currentItem = 3
                days = data.toString().toInt()
            }
            4 -> {
                type = data.toString().toInt()

                val intent_loading = Intent(this, Recommend_Loading::class.java)
                intent_loading.putExtra("city", city)
                intent_loading.putExtra("companion", companion)
                intent_loading.putExtra("days", days)
                intent_loading.putExtra("type", type)
                startActivity(intent_loading)
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            }
        }
    }
}