package hong.sy.chowall

import android.content.Intent
import android.graphics.Paint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.WindowCompat
import hong.sy.chowall.databinding.ActivityInitBinding

class InitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitBinding
    private lateinit var content: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setButtonClickListener()
        setTextClickListener()
        setContentColor()
    }

    private fun setToolbar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun setButtonClickListener() {
        binding.btnLoginInit.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTextClickListener() {
        binding.tvRegister.setOnClickListener {
            binding.tvRegister.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setContentColor() {
        content = binding.tvRegister
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorHintSpan = ForegroundColorSpan(getResources().getColor(R.color.hintColor))
        builder.setSpan(colorHintSpan, textData.length-4, textData.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }
}