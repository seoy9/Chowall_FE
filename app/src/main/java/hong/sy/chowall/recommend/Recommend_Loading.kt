package hong.sy.chowall.recommend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.MenuItem
import android.widget.TextView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.FadingCircle
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityRecommendLoadingBinding

class Recommend_Loading : AppCompatActivity() {
    private lateinit var binding: ActivityRecommendLoadingBinding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setProgressBar()

        binding.progressbarRecommend.setOnClickListener {
            val intent_result = Intent(this, Recommend_Result::class.java)
            startActivity(intent_result)
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarReLoading
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
        content = binding.tvSearchReLoading
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, 10, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setProgressBar() {
        val progressBar = binding.progressbarRecommend
        val fadingCircle : Sprite = FadingCircle()
        progressBar.setIndeterminateDrawable(fadingCircle)
    }
}