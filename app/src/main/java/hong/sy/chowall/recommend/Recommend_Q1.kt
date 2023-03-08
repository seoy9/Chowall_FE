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
import hong.sy.chowall.databinding.ActivityRecommendQ1Binding

class Recommend_Q1 : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendQ1Binding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendQ1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setBottomNavigation()
        setButton()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarReQ1
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
        content = binding.tvQ1
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setBottomNavigation() {
        binding.bottomNavReQ1.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    val intent_main = Intent(this, MainActivity::class.java)
                    startActivity(intent_main)
                    finish()
                }
            }
        }
    }

    private fun setButton() {
        binding.btnChuncheonQ1.setOnClickListener {
            val intent_q2 = Intent(this, Recommend_Q2::class.java)
            startActivity(intent_q2)
        }

        binding.btnGangneungQ1.setOnClickListener {
            val intent_q2 = Intent(this, Recommend_Q2::class.java)
            startActivity(intent_q2)
        }
    }
}