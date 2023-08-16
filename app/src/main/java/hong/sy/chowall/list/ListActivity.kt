package hong.sy.chowall.list

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewpager2.widget.ViewPager2
import hong.sy.chowall.*
import hong.sy.chowall.databinding.ActivityListBinding
import hong.sy.chowall.recommend.RecViewPagerAdapter
import hong.sy.chowall.recommend.ResultData
import kotlinx.coroutines.*

class ListActivity : HideSoftKey() {
    private lateinit var binding: ActivityListBinding
    private var chunDatas = arrayListOf<ListData>()
    private var gangDatas = arrayListOf<ListData>()
    private var jeonDatas = arrayListOf<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                chunDatas = intent.getSerializableExtra("chunDatas") as ArrayList<ListData>
                gangDatas = intent.getSerializableExtra("gangDatas") as ArrayList<ListData>
                jeonDatas = intent.getSerializableExtra("jeonDatas") as ArrayList<ListData>
            }.await()

            Log.d("레트로핏 목록", "${chunDatas.size}")
            Log.d("레트로핏 목록", "${gangDatas.size}")
            Log.d("레트로핏 목록", "${jeonDatas.size}")

            val pagerAdapter = ListViewPagerAdapter(this@ListActivity)
            pagerAdapter.addFragment(ListChunFragment(chunDatas))
            pagerAdapter.addFragment(ListGangFragment(gangDatas))
            pagerAdapter.addFragment(ListJeonFragment(jeonDatas))

            binding.listViewpager.adapter = pagerAdapter
            binding.listViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            setToolbar()
            setButton()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarList
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

    private fun setButton() {
        val btnChun = binding.btnListChun
        val btnGang = binding.btnListGang
        val btnJeon = binding.btnListJeon

        btnChun.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.listViewpager.currentItem = 0

                btnGang.isChecked = false
                btnJeon.isChecked = false
            }
        }

        btnGang.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.listViewpager.currentItem = 1

                btnChun.isChecked = false
                btnJeon.isChecked = false
            }
        }

        btnJeon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.listViewpager.currentItem = 2

                btnChun.isChecked = false
                btnGang.isChecked = false
            }
        }

        btnChun.setOnClickListener {
            if(!btnChun.isChecked) {
                btnChun.isChecked = true
            }
        }

        btnGang.setOnClickListener {
            if(!btnGang.isChecked) {
                btnGang.isChecked = true
            }
        }

        btnJeon.setOnClickListener {
            if(!btnJeon.isChecked) {
                btnJeon.isChecked = true
            }
        }
    }
}