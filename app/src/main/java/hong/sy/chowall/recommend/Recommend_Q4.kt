package hong.sy.chowall.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.widget.TextView
import hong.sy.chowall.MainActivity
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityRecommendQ4Binding

class Recommend_Q4 : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendQ4Binding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendQ4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setBottomNavigation()
        setToggleButtonChecked()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarMain
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setContentColor() {
        content = binding.tvQ4
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setBottomNavigation() {
        binding.bottomNav.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setToggleButtonChecked() {
        binding.btnRefreshQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRestaurantQ4.isChecked = false
                binding.btnCafeQ4.isChecked = false
                binding.btnCultureQ4.isChecked = false
                binding.btnHotplaceQ4.isChecked = false
                binding.btnActivityQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }

        binding.btnRestaurantQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRefreshQ4.isChecked = false
                binding.btnCafeQ4.isChecked = false
                binding.btnCultureQ4.isChecked = false
                binding.btnHotplaceQ4.isChecked = false
                binding.btnActivityQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }

        binding.btnCafeQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRefreshQ4.isChecked = false
                binding.btnRestaurantQ4.isChecked = false
                binding.btnCultureQ4.isChecked = false
                binding.btnHotplaceQ4.isChecked = false
                binding.btnActivityQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }

        binding.btnCultureQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRefreshQ4.isChecked = false
                binding.btnRestaurantQ4.isChecked = false
                binding.btnCafeQ4.isChecked = false
                binding.btnHotplaceQ4.isChecked = false
                binding.btnActivityQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }

        binding.btnHotplaceQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRefreshQ4.isChecked = false
                binding.btnRestaurantQ4.isChecked = false
                binding.btnCafeQ4.isChecked = false
                binding.btnCultureQ4.isChecked = false
                binding.btnActivityQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }

        binding.btnActivityQ4.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRefreshQ4.isChecked = false
                binding.btnRestaurantQ4.isChecked = false
                binding.btnCafeQ4.isChecked = false
                binding.btnCultureQ4.isChecked = false
                binding.btnHotplaceQ4.isChecked = false

                val intent = Intent(this, Recommend_Result::class.java)
                startActivity(intent)
            }
        }
    }
}