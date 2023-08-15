package hong.sy.chowall

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
import hong.sy.chowall.databinding.ActivityMainBinding
import hong.sy.chowall.list.ListActivity
import hong.sy.chowall.list.ListData
import hong.sy.chowall.list.ListResultActivity
import hong.sy.chowall.recommend.RecommendActivity
import hong.sy.chowall.recommend.Recommend_Result
import hong.sy.chowall.recommend.ResultData
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class MainActivity : HideSoftKey() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBar()
        setRecButton()
        setListButton()
    }

    private fun setStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun setRecButton() {
        binding.btnMainRec.setOnClickListener {
            val intent_recommend = Intent(this, RecommendActivity::class.java)
            intent_recommend.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent_recommend)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }

    private fun setListButton() {
        binding.btnMainList.setOnClickListener {
            val intent_list = Intent(this, ListResultActivity::class.java)
            val chunDatas = mutableListOf<ListData>()
            val gangDatas = mutableListOf<ListData>()
            val jeonDatas = mutableListOf<ListData>()
            val dialog = LoadingDialog(this@MainActivity)
            dialog.show()

            CoroutineScope(Dispatchers.Main).launch {
//                CoroutineScope(Dispatchers.IO).async {
                    CoroutineScope(Dispatchers.IO).async {
                        getSearchList(chunDatas, "춘천시")
//                        getSearchList(gangDatas, "강원시")
//                        getSearchList(jeonDatas, "전주시")
                        delay(1000)
                    }.await()
//                CoroutineScope(Dispatchers.IO).async {
//                    getSearchList(gangDatas, "강원시")
//                    delay(2000)
//                }.await()
//                CoroutineScope(Dispatchers.IO).async {
//                    getSearchList(jeonDatas, "전주시")
//                    delay(2000)
//                }.await()

//                CoroutineScope(Dispatchers.IO).async {
//                    getImage(chunDatas, "춘천시")
//                    getImage(gangDatas, "강원시")
//                    getImage(jeonDatas, "전주시")
//                    delay(15000)
//                }.await()

                intent_list.putExtra("chunDatas", ArrayList<ListData>(chunDatas))
//                intent_list.putExtra("gangDatas", ArrayList<ListData>(gangDatas))
//                intent_list.putExtra("jeonDatas", ArrayList<ListData>(jeonDatas))
                dialog.dismiss()
                startActivity(intent_list)
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
            }
        }
    }

    private fun getSearchList(datas: MutableList<ListData>, where: String) {
        val retrofitAPI = RetrofitConnection.getInstance().create(SearchService::class.java)

        retrofitAPI.getSearchList(
            0, 10, "city", where
        ).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let { searchList(it, datas, where) }
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