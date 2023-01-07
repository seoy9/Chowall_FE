package hong.sy.chowall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        setToolbar()
        setContentColor()
        setRecyclerView()
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

    private fun setRecyclerView() {
        val rvRecommend = binding.rvRecommendCourse
        val rvPopular = binding.rvPopularCourse

        rvRecommend.adapter = CourseCardAdapter(getList(), this)
        rvRecommend.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        rvPopular.adapter = CourseCardAdapter(getList(), this)
        rvPopular.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun getList(): ArrayList<CourseCard> {
        var courseCards = arrayListOf<CourseCard>()

        //image 는 Figma 에서 1.5배 적용해서 저장
        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))
        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))

        return courseCards
    }

//    private fun getList(): ArrayList<Int> {
//        return arrayListOf<Int>(R.drawable.img_course_list, R.drawable.init_background, R.drawable.img_course_list_2, R.drawable.btn_make_course)
//    }
}