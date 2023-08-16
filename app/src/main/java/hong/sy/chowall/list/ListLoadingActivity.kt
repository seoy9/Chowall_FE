package hong.sy.chowall.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.FadingCircle
import hong.sy.chowall.R
import hong.sy.chowall.databinding.ActivityListLoadingBinding
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListLoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListLoadingBinding
    private lateinit var content: TextView
    private val chunDatas = mutableListOf<ListData>()
    private val gangDatas = mutableListOf<ListData>()
    private val jeonDatas = mutableListOf<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setContentColor()
        setProgressBar()

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                getSearchList(chunDatas, "춘천시")
                delay(1500)
            }.join()
            CoroutineScope(Dispatchers.IO).async {
                getSearchList(gangDatas, "강릉시")
                delay(1500)
            }.join()
            CoroutineScope(Dispatchers.IO).async {
                getSearchList(jeonDatas, "전주시")
                delay(1500)
            }.join()

            val intent_list = Intent(this@ListLoadingActivity, ListActivity::class.java)
            intent_list.putExtra("chunDatas", ArrayList<ListData>(chunDatas))
            intent_list.putExtra("gangDatas", ArrayList<ListData>(gangDatas))
            intent_list.putExtra("jeonDatas", ArrayList<ListData>(jeonDatas))
            startActivity(intent_list)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbarListLoading
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

    private fun setContentColor() {
        content = binding.tvListLoadingWhat
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(getResources().getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }


    private fun setProgressBar() {
        val progressBar = binding.progressbarList
        val fadingCircle : Sprite = FadingCircle()
        progressBar.setIndeterminateDrawable(fadingCircle)
    }

    private fun getSearchList(datas: MutableList<ListData>, where: String) {
        val retrofitAPI = RetrofitConnection.getInstance().create(SearchService::class.java)

        retrofitAPI.getSearchList(
            0, 20, "city", where
        ).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let { searchList(it, datas, where) }
                    Log.d("레트로핏 ${where}", "${where} 업데이트 성공")
                } else {
                    Log.d("레트로핏 ${where}", response.toString())
                    Log.d("레트로핏 ${where}", "${where} 업데이트 실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("레트로핏 ${where}", "${where} 업데이트 실패 onFailure")
            }
        })
    }

    private fun searchList(resultData : SearchResponse, datas: MutableList<ListData>, where: String) {
        val retrofitAPI = RetrofitConnection.getInstance().create(TouristAttractionSingleService::class.java)

        for(it in resultData.data.content) {
            retrofitAPI.getTouristAttractionsingleData(it.id).enqueue(object :
                Callback<TouristAttractionSingleResponse> {
                override fun onResponse(
                    call: Call<TouristAttractionSingleResponse>,
                    response: Response<TouristAttractionSingleResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { getList(it, datas, where) }
                        Log.d("레트로핏 ${where}2", "${where}2 업데이트 성공")
                    } else {
                        Log.d("레트로핏 ${where}2", "${where}2 업데이트 실패")
                    }
                }

                override fun onFailure(call: Call<TouristAttractionSingleResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("레트로핏 ${where}2", "${where}2 업데이트 실패 onFailure")
                }
            })
        }
    }

    private fun getList(list : TouristAttractionSingleResponse, datas: MutableList<ListData>, where: String) {
        val data = ListData(list.data.attractionId, isNull(list.data.name), isNull(list.data.address),
            isNull(list.data.number), isNull(list.data.openingHours), isNull(list.data.breakTime), list.data.hasRamp,
            list.data.hasToilet, list.data.hasParking, list.data.hasLift, list.data.companionRequired,
            list.data.hasWheelchair, isNull(list.data.attractionType), list.data.imgId, isNull(list.data.url))

        Log.d("레트로핏 ${where}3", "${data}")

        datas.add(data)
    }

    private fun isNull(i : String) : String {
        if(i == null) {
            return ""
        }
        return i
    }
}