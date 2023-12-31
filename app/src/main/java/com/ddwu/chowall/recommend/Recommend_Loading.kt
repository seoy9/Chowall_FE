package com.ddwu.chowall.recommend

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import com.ddwu.chowall.HideSoftKey
import com.ddwu.chowall.R
import com.ddwu.chowall.databinding.ActivityRecommendLoadingBinding
import com.ddwu.chowall.retrofit.RecommendResponse
import com.ddwu.chowall.retrofit.RecommendService
import com.ddwu.chowall.retrofit.RetrofitConnection
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response

class Recommend_Loading : HideSoftKey() {
    private lateinit var binding: ActivityRecommendLoadingBinding
    private lateinit var content: TextView
    private var city : String = ""
    private var companion : Int = 0
    private var days : Int = 0
    private var type : Int = 0
    private var datas = arrayListOf<ResultData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor1()
        setContentColor2()
        setProgressBar()

        city = intent.getStringExtra("city").toString()
        companion = intent.getIntExtra("companion", 0)
        days = intent.getIntExtra("days", 0)
        type = intent.getIntExtra("type", 0)


        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                getRecommendList()
                delay(2000)
            }.join()

            val intent_result = Intent(this@Recommend_Loading, Recommend_Result::class.java)
            intent_result.putExtra("datas", ArrayList<ResultData>(datas))
            startActivity(intent_result)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarReLoading
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
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

    private fun setContentColor1() {
        content = binding.tvRecLoadingFor
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setContentColor2() {
        content = binding.tvRecLoadingSearch
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

    fun getRecommendList() {
        val retrofitAPI = RetrofitConnection.getInstance().create(RecommendService::class.java)

        retrofitAPI.getRecommendList(
            city, companion, days, type
        ).enqueue(object : retrofit2.Callback<RecommendResponse> {
            override fun onResponse(
                call: Call<RecommendResponse>,
                response: Response<RecommendResponse>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let { getResultList(it) }
                } else {
                    Log.d("레트로핏", response.toString())
                    Log.d("레트로핏", "추천 업데이트 실패")
                }
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("레트로핏", "추천 업데이트 실패 onFailure")
            }
        })
    }

    private fun getResultList(list : RecommendResponse) {
        for(it in list.data.touristAttractionDtos) {
            val str = it.toString().replace("{", "").replace("}", "")
            val objects = str.split(",")

            val attractionId = objects[0].split("=").get(1).replace(".0", "").toInt()
            val name = isNull(objects[1].split("=").get(1))
            val address = isNull(objects[2].split("=").get(1))
            val number = isNull(objects[3].split("=").get(1))
            val openingHours = isNull(objects[4].split("=").get(1))
            val breakTime = isNull(objects[5].split("=").get(1))
            val hasRamp = objects[6].split("=").get(1).toBoolean()
            val hasToilet = objects[7].split("=").get(1).toBoolean()
            val hasParking = objects[8].split("=").get(1).toBoolean()
            val hasLift = objects[9].split("=").get(1).toBoolean()
            val companionRequired = objects[10].split("=").get(1).toBoolean()
            val hasWheelchair = objects[11].split("=").get(1).toBoolean()
            val attractionType = isNull(objects[12].split("=").get(1))
            val imgId = objects[13].split("=").get(1).replace(".0", "").toInt()
            val url = isNull(objects[14].split("=").get(1))

            val data = ResultData(attractionId, name, address, number, openingHours, breakTime, hasRamp, hasToilet, hasParking, hasLift, companionRequired, hasWheelchair, attractionType, imgId, url)

            datas.add(data)
        }
    }

    private fun isNull(i : String) : String {
        if(i == "null") {
            return ""
        }
        return i
    }
}