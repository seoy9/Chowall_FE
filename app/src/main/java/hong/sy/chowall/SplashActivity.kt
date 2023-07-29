package hong.sy.chowall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import hong.sy.chowall.databinding.ActivitySplashBinding
import java.time.Duration

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var content: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentColor()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        }, DURATION)
    }

    companion object {
        private const val DURATION: Long = 3000
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setContentColor() {
        content = binding.tvSplash
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(colorMainSpan, 23, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(colorMainSpan, 38, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }
}