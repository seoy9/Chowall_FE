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
import kotlinx.coroutines.*

class Recommend_Result : HideSoftKey() {
    private lateinit var binding: ActivityRecommendResultBinding
    private lateinit var content: TextView
    private lateinit var resultAdapter: ResultRecyclerAdapter
    private var datas = arrayListOf<ResultData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                datas = intent.getSerializableExtra("datas") as ArrayList<ResultData>
            }.await()

            setToolbar()
            setContentColor()
            initRecycler()
        }
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

        resultAdapter.datas = datas
        resultAdapter.notifyDataSetChanged()
    }
}
