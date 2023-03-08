package hong.sy.chowall

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.ActivityMainBinding
import hong.sy.chowall.recommend.Recommend_Q1
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resizingViewHeight()
        setToolbar()
        setContentColor()
        setRecyclerView()
        setBottomNavItemSize()

        binding.btnRecommend.setOnClickListener {
            val intent_q1 = Intent(this, Recommend_Q1::class.java)
            startActivity(intent_q1)
        }
    }

    private fun setBottomNavItemSize() {
        if (getDeviceHeight() >= 3088) {
            binding.bottomNavMain.itemIconSize = (60 * resources.displayMetrics.density).roundToInt()
        }
    }

    private fun resizingViewHeight() {
        val deviceHeight = getDeviceHeight()
        resizeViewHeight(deviceHeight, binding.scroll, 1.23)
    }

    private fun Context.getDeviceHeight(): Int {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if(Build.VERSION.SDK_INT >= 30) {
            windowManager.currentWindowMetrics.bounds.height()
        } else {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            size.y
        }
    }

    private fun resizeViewHeight(deviceHeight: Int, view: View, height: Double) {
        val layoutParams = view.layoutParams
        layoutParams.height = (deviceHeight * height).toInt()
        view.layoutParams = layoutParams
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
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
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

        rvRecommend.addItemDecoration(HorizontalItemDecorator(40))
        rvPopular.addItemDecoration(HorizontalItemDecorator(40))
    }

    private fun getList(): ArrayList<CourseCard> {
        val courseCards = arrayListOf<CourseCard>()

        courseCards.add(CourseCard("init_background", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))
        courseCards.add(CourseCard("card_course_1", "card_desc_1"))
        courseCards.add(CourseCard("card_course_2", "card_desc_2"))

        return courseCards
    }
}