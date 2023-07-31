package hong.sy.chowall.recommend

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import hong.sy.chowall.*
import hong.sy.chowall.databinding.ActivityRecommendResultBinding

class Recommend_Result : HideSoftKey() {
    private lateinit var binding: ActivityRecommendResultBinding
    private lateinit var content: TextView
    private lateinit var resultAdapter: ResultRecyclerAdapter
    val datas = mutableListOf<ResultData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        initRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarRecResult
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent_main = Intent(this, MainActivity::class.java)
                intent_main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent_main)
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            }
            R.id.toolbar_pic_info -> {
                InfoDialog(this).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setContentColor() {
        content = binding.tvRecResult
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun initRecycler() {
        resultAdapter = ResultRecyclerAdapter(this)
        binding.rvRecResult.adapter = resultAdapter
        binding.rvRecResult.addItemDecoration(VerticalItemDecorator(70))

        datas.apply {
            add(ResultData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ResultData(img = R.drawable.ex_img_list, name = "델모니코스", address = "강원도 춘천시 동면 순환대로 1154-106", phone = "033-252-0999", time = "11:00-22:00" ))
            add(ResultData(img = R.drawable.ex_img_list, name = "남부닭갈비", address = "강원도 춘천시 공지로 357", phone = "033-243-9966", time = "매일 17:00-22:00" ))
            add(ResultData(img = R.drawable.ex_img_list, name = "라모스버거", address = "강원도 춘천시 옛경춘로 835", phone = "0507-1402-0006", time = "매일 11:00-22:00", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ResultData(img = R.drawable.ex_img_list, name = "온더가든", address = "강원도 춘천시 남산면 종자리로 21", phone = "033-262-9339", time = "매일 10:00-22:00" ))
            add(ResultData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
        }

        resultAdapter.datas = datas
        resultAdapter.notifyDataSetChanged()
    }
}