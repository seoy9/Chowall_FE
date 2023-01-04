package hong.sy.chowall

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import hong.sy.chowall.databinding.TempMainCardBinding

class TempMainCardActivity : AppCompatActivity() {
    private lateinit var binding: TempMainCardBinding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TempMainCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setCourseCardContent()
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

    private fun setCourseCardContent() {
        val viewPager = binding.courseViewPagerMain
        var courseCards = arrayListOf<CourseCard>()

        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))
        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))

        var adapter = CourseCardAdapter(courseCards, this)
        viewPager.adapter = adapter
        viewPager.setPadding(50, 0, 50, 0)

        /* 여백, 너비에 대한 정의 */
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels // 스마트폰의 너비 길이를 가져옴
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        viewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        viewPager.offscreenPageLimit = adapter.itemCount // 몇 개의 페이지를 미리 로드 해둘것인지

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
}