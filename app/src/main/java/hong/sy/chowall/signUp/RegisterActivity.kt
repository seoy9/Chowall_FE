package hong.sy.chowall.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgPwCheckbox.setImageBitmap(null)

        setToolbar()
        setButtonClickListener()
        setPasswordCheckBox()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarRegis
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)
        ab.setHomeAsUpIndicator(R.drawable.back_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setButtonClickListener() {
        binding.btnNextRegis.setOnClickListener {
            val intent = Intent(this, RestrictionsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setPasswordCheckBox() {
        binding.edPwRegis.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                binding.imgPwCheckbox.setImageBitmap(null)
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                binding.imgPwCheckbox.setImageBitmap(null)
            }
        })
        binding.edPwCheckRegis.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    when {
                        p0.isEmpty() -> {
                            binding.imgPwCheckbox.setImageBitmap(null)
                        }
                        p0.isNotEmpty() -> {
                            if (binding.edPwRegis.text.toString() != ""
                                && binding.edPwCheckRegis.text.toString() != binding.edPwRegis.text.toString()) {
                                binding.imgPwCheckbox.setImageBitmap(null)
                            } else {
                                binding.imgPwCheckbox.setImageResource(R.drawable.img_pw_check)
                            }
                        }
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }
}