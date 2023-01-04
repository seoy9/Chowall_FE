package hong.sy.chowall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import hong.sy.chowall.databinding.ActivityMainBinding
import hong.sy.chowall.viewPager.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setViewPager()

        setToolbar()
        setContentColor()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarMain
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
    }

    private fun setContentColor() {
        content = binding.tvSloganMain
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, textData.length-4, textData.length-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setViewPager() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.vpRecommendCourse.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.vpRecommendCourse.offscreenPageLimit = 2
        binding.vpRecommendCourse.adapter = ViewPagerAdapter(getList())
        binding.vpRecommendCourse.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.vpChowallCourse.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.vpChowallCourse.offscreenPageLimit = 2
        binding.vpChowallCourse.adapter = ViewPagerAdapter(getList())
        binding.vpChowallCourse.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun getList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.img_course_list, R.drawable.init_background, R.drawable.img_course_list_2, R.drawable.btn_make_course)
    }
}