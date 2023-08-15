package hong.sy.chowall.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import hong.sy.chowall.InfoDialog
import hong.sy.chowall.MainActivity
import hong.sy.chowall.R
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.ActivityListResultBinding
import hong.sy.chowall.databinding.ActivityRecommendResultBinding
import hong.sy.chowall.recommend.ResultData
import hong.sy.chowall.recommend.ResultRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListResultBinding
    private lateinit var content: TextView
    private lateinit var resultAdapter: ListRecyclerAdapter
    private var datas = arrayListOf<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                datas = intent.getSerializableExtra("datas") as ArrayList<ListData>
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
        val toolbar = binding.toolbarListResult
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
        content = binding.tvListResult
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun initRecycler() {
        resultAdapter = ListRecyclerAdapter(this)
        binding.rvListResult.adapter = resultAdapter
        binding.rvListResult.addItemDecoration(VerticalItemDecorator(70))

        resultAdapter.datas = datas
        resultAdapter.notifyDataSetChanged()
    }
}