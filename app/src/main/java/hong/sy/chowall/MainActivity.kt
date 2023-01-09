package hong.sy.chowall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.ActivityMainBinding

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

        courseCards.add(CourseCard("init_background", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))
        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))

        return courseCards
    }
}