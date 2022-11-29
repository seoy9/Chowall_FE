package hong.sy.chowall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hong.sy.chowall.databinding.ActivityTemporaryBinding

class TemporaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemporaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTemporaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setBtnClickListener()
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarTemp
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
    }

    private fun setBtnClickListener() {
        binding.btnRes.setOnClickListener {
            val intent = Intent(this, RestrictionsActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}