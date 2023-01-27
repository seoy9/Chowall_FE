package hong.sy.chowall.signUp

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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

    private var nameFlag = false
    private var idFlag = false
    private var emailFlag = false
    private var pwFlag = false
    private var pwCheckFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPaddingTextView()
        setToolbar()
        setButtonClickListener()
        flagCheck()
        setTextViewInputLayout()
    }

    private fun Context.getDeviceHeight(): Int {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if(Build.VERSION.SDK_INT >= 30) {
            windowManager.currentWindowMetrics.bounds.height()
        } else {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            size.y
        }
    }

    private fun setPaddingTextView() {
        val height = getDeviceHeight()
        val standardHeight = 3088

        if (height < standardHeight) {
            binding.nameTextviewInputLayout.editText?.setPadding(30, 30, 30, 30)
            binding.idTextviewInputLayout.editText?.setPadding(30, 30, 30, 30)
            binding.emailTextviewInputLayout.editText?.setPadding(30, 30, 30, 30)
            binding.pwTextviewInputLayout.editText?.setPadding(30, 30, 30, 30)
            binding.pwCheckTextviewInputLayout.editText?.setPadding(30, 30, 30, 30)
        }
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

    private val nameListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.edNameRegis.error = resources.getString(R.string.regis_error_name)
                        nameFlag = false
                    }
                    else -> {
                        binding.edNameRegis.error = null
                        nameFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.edIdRegis.error = resources.getString(R.string.regis_error_id)
                        idFlag = false
                    }
                    !idRegex(s.toString()) -> {
                        binding.edIdRegis.error = resources.getString(R.string.regis_error_id_regex)
                        idFlag = false
                    }
                    else -> {
                        binding.edIdRegis.error = null
                        idFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    private val emailListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.edEmailRegis.error = resources.getString(R.string.regis_error_email)
                        emailFlag = false
                    }
                    !emailRegex(s.toString()) -> {
                        binding.edEmailRegis.error = resources.getString(R.string.regis_error_email_regex)
                        emailFlag = false
                    }
                    else -> {
                        binding.edEmailRegis.error = null
                        emailFlag = true
                    }
                }
                flagCheck()
            }
        }
    }

    private val pwListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.edPwRegis.error = resources.getString(R.string.regis_error_pw)
                        pwFlag = false
                    }
                    !passwordRegex(s.toString()) -> {
                        binding.edPwRegis.error = resources.getString(R.string.regis_error_pw_regex)
                        pwFlag = false
                    }
                    s.isNotEmpty() -> {
                        binding.edPwRegis.error = null
                        pwFlag = true
                        if (binding.edPwRegis.text.toString() != ""
                            && binding.edPwCheckRegis.text.toString() != binding.edPwRegis.text.toString()) {
                            binding.edPwCheckRegis.error = resources.getString(R.string.regis_error_pw_equal)
                            pwFlag = true
                            pwCheckFlag = false
                        } else {
                            binding.edPwCheckRegis.error = null
                            pwCheckFlag = true
                        }
                    }
                }
                flagCheck()
            }
        }
    }

    private val pwCheckListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    s.isEmpty() -> {
                        binding.edPwCheckRegis.error = resources.getString(R.string.regis_error_repw)
                        pwCheckFlag = false
                    }
                    s.isNotEmpty() -> {
                        if (binding.edPwRegis.text.toString() != ""
                            && binding.edPwCheckRegis.text.toString() != binding.edPwRegis.text.toString()) {
                            binding.edPwCheckRegis.error = resources.getString(R.string.regis_error_pw_equal)
                            pwFlag = true
                            pwCheckFlag = false
                        } else {
                            binding.edPwCheckRegis.error = null
                            pwCheckFlag = true
                        }
                    }
                }
                flagCheck()
            }
        }
    }

    private fun idRegex(id: String): Boolean {
        return id.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$".toRegex())
    }

    private fun emailRegex(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun passwordRegex(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,10}$".toRegex())
    }

    private fun flagCheck() {
        binding.btnNextRegis.isEnabled = nameFlag && idFlag && emailFlag && pwFlag && pwCheckFlag
    }

    private fun setTextViewInputLayout() {
        binding.nameTextviewInputLayout.editText?.addTextChangedListener(nameListener)
        binding.emailTextviewInputLayout.editText?.addTextChangedListener(emailListener)
        binding.pwCheckTextviewInputLayout.editText?.addTextChangedListener(pwCheckListener)

        binding.idTextviewInputLayout.editText?.addTextChangedListener(idListener)
        binding.edIdRegis.hint = resources.getString(R.string.regis_id_hint)
        binding.edIdRegis.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edIdRegis.hint = ""
            } else {
                binding.edIdRegis.hint = resources.getString(R.string.regis_id_hint)
            }
        }

        binding.pwTextviewInputLayout.editText?.addTextChangedListener(pwListener)
        binding.edPwRegis.hint = resources.getString(R.string.regis_pw_hint)
        binding.edPwRegis.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.edPwRegis.hint = ""
            } else {
                binding.edPwRegis.hint = resources.getString(R.string.regis_pw_hint)
            }
        }
    }
}