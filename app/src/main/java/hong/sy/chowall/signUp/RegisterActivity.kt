package hong.sy.chowall.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityRegisterBinding
import hong.sy.chowall.retrofit.DataService
import hong.sy.chowall.retrofit.RetrofitConnection
import hong.sy.chowall.retrofit.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            setUserData()
            val intent = Intent(this, RestrictionsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUser() : User {
        val user = User()
        user.id = binding.edIdRegis.text.toString()
        user.name = binding.edNameRegis.text.toString()
        user.password = binding.edPwRegis.text.toString()
        user.email = binding.edEmailRegis.text.toString()

        return user
    }

    private fun setUserData() {
        val retrofitAPI = RetrofitConnection.getInstance().create(DataService::class.java)

        retrofitAPI.getRegisterResponse(setUser())
            .enqueue(object : Callback<String> { // 비동기 방식 통신 메소드
                override fun onResponse( // 통신에 성공한 경우
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful) { // 응답 잘 받은 경우
                        Log.d("Request", "응답 잘 받음")
                        Log.d("RESPONSE: ", response.body().toString())

                    } else {
                        // 통신 성공 but 응답 실패
                        Log.d("Request", "통신 성공 but 응답 실패")
                        Log.d("RESPONSE", "FAILURE")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    // 통신에 실패한 경우
                    Log.d("Request", "통신 실패")
                    Log.d("CONNECTION FAILURE: ", t.localizedMessage)
                }
            })
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