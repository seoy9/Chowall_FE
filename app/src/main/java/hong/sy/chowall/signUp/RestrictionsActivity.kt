package hong.sy.chowall.signUp

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
import hong.sy.chowall.databinding.ActivityRestrictionsBinding

class RestrictionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestrictionsBinding
    private lateinit var content: TextView
    private var checked = arrayOf(false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestrictionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setCheckBoxClickListener()
        setTextClickListener()
        setButtonClickListener()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarRest
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)
        ab.setHomeAsUpIndicator(R.drawable.back_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setContentColor() {
        content = binding.tvSloganRest
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, textData.length-4, textData.length-2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setCheckBoxClickListener() {
        binding.btnA1.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[0]) {
                binding.btnA1.setBackgroundResource(R.drawable.check_box)
                checked[0] = false
            } else {
                binding.btnA1.setBackgroundResource(R.drawable.checked_box)
                checked[0] = true
            }
        }

        binding.btnA2.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[1]) {
                binding.btnA2.setBackgroundResource(R.drawable.check_box)
                checked[1] = false
            } else {
                binding.btnA2.setBackgroundResource(R.drawable.checked_box)
                checked[1] = true
            }
        }

        binding.btnA3.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[2]) {
                binding.btnA3.setBackgroundResource(R.drawable.check_box)
                checked[2] = false
            } else {
                binding.btnA3.setBackgroundResource(R.drawable.checked_box)
                checked[2] = true
            }
        }

        binding.btnA4.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[3]) {
                binding.btnA4.setBackgroundResource(R.drawable.check_box)
                checked[3] = false
            } else {
                binding.btnA4.setBackgroundResource(R.drawable.checked_box)
                checked[3] = true
            }
        }

        binding.btnA5.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            } else {
                binding.btnA1.setBackgroundResource(R.drawable.check_box)
                binding.btnA2.setBackgroundResource(R.drawable.check_box)
                binding.btnA3.setBackgroundResource(R.drawable.check_box)
                binding.btnA4.setBackgroundResource(R.drawable.check_box)
                binding.btnA5.setBackgroundResource(R.drawable.checked_box)
                checked[0] = false
                checked[1] = false
                checked[2] = false
                checked[3] = false
                checked[4] = true
            }
        }
    }

    private fun setTextClickListener() {
        binding.tvA1.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[0]) {
                binding.btnA1.setBackgroundResource(R.drawable.check_box)
                checked[0] = false
            } else {
                binding.btnA1.setBackgroundResource(R.drawable.checked_box)
                checked[0] = true
            }
        }

        binding.tvA2.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[1]) {
                binding.btnA2.setBackgroundResource(R.drawable.check_box)
                checked[1] = false
            } else {
                binding.btnA2.setBackgroundResource(R.drawable.checked_box)
                checked[1] = true
            }
        }

        binding.tvA3.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[2]) {
                binding.btnA3.setBackgroundResource(R.drawable.check_box)
                checked[2] = false
            } else {
                binding.btnA3.setBackgroundResource(R.drawable.checked_box)
                checked[2] = true
            }
        }

        binding.tvA4.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            }
            if(checked[3]) {
                binding.btnA4.setBackgroundResource(R.drawable.check_box)
                checked[3] = false
            } else {
                binding.btnA4.setBackgroundResource(R.drawable.checked_box)
                checked[3] = true
            }
        }

        binding.tvA5.setOnClickListener {
            if(checked[4]) {
                binding.btnA5.setBackgroundResource(R.drawable.check_box)
                checked[4] = false
            } else {
                binding.btnA1.setBackgroundResource(R.drawable.check_box)
                binding.btnA2.setBackgroundResource(R.drawable.check_box)
                binding.btnA3.setBackgroundResource(R.drawable.check_box)
                binding.btnA4.setBackgroundResource(R.drawable.check_box)
                binding.btnA5.setBackgroundResource(R.drawable.checked_box)
                checked[0] = false
                checked[1] = false
                checked[2] = false
                checked[3] = false
                checked[4] = true
            }
        }
    }

    fun setButtonClickListener() {
        binding.btnSignupRest.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}