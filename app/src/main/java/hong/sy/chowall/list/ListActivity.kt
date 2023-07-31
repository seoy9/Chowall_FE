package hong.sy.chowall.list

import android.app.AlertDialog
import android.os.Bundle
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

class ListActivity : HideSoftKey() {
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listViewpager.adapter = ListViewPagerAdapter(this)
        binding.listViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        setToolbar()
        setButton()
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
                finish()
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

        btnChun.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.listViewpager.currentItem = 0

                btnGang.isChecked = false
                btnJeon.isChecked = false
            }
        }

        btnGang.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.listViewpager.currentItem = 1

                btnChun.isChecked = false
                btnJeon.isChecked = false
            }
        }

        btnJeon.setOnCheckedChangeListener { button, isChecked ->
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