package hong.sy.chowall.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import hong.sy.chowall.*
import hong.sy.chowall.databinding.ActivityListBinding

class ListActivity : HideSoftKey() {
    private lateinit var binding: ActivityListBinding
    private lateinit var listAdapter: ListAdapter
    val datas = mutableListOf<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setButton()
        initRecycler()
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
//                val intent_main = Intent(this, MainActivity::class.java)
//                intent_main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                startActivity(intent_main)
                finish()
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setButton() {
        binding.btnListChun.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnListGang.isChecked = false
                binding.btnListJeon.isChecked = false
            }
        }

        binding.btnListGang.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnListChun.isChecked = false
                binding.btnListJeon.isChecked = false
            }
        }

        binding.btnListJeon.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnListChun.isChecked = false
                binding.btnListGang.isChecked = false
            }
        }
    }

    private fun initRecycler() {
        listAdapter = ListAdapter(this)
        binding.rvList.adapter = listAdapter
        binding.rvList.addItemDecoration(VerticalItemDecorator(70))
//        binding.rvList.addItemDecoration(HorizontalItemDecorator(18))

        datas.apply {
            add(ListData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ListData(img = R.drawable.ex_img_list, name = "델모니코스", address = "강원도 춘천시 동면 순환대로 1154-106", phone = "033-252-0999", time = "11:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "남부닭갈비", address = "강원도 춘천시 공지로 357", phone = "033-243-9966", time = "매일 17:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "라모스버거", address = "강원도 춘천시 옛경춘로 835", phone = "0507-1402-0006", time = "매일 11:00-22:00", breakTime = "(브레이크타임 15:00-16:00)" ))
            add(ListData(img = R.drawable.ex_img_list, name = "온더가든", address = "강원도 춘천시 남산면 종자리로 21", phone = "033-262-9339", time = "매일 10:00-22:00" ))
            add(ListData(img = R.drawable.ex_img_list, name = "곰배령", address = "강원도 춘천시 춘천로 1층", phone = "033-255-5500", time = "매일 11:30-20:20", breakTime = "(브레이크타임 15:00-16:00)" ))
        }

        listAdapter.datas = datas
        listAdapter.notifyDataSetChanged()
    }
}