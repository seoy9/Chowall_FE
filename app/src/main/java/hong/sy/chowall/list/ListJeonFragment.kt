package hong.sy.chowall.list

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListJeonBinding
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListJeonFragment(data: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListJeonBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()
    val jeonDatas = data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListJeonBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecycler()

//        CoroutineScope(Dispatchers.Main).launch {
//            CoroutineScope(Dispatchers.IO).async {
//                getSearchJeonList()
//            }.await()
//
//            CoroutineScope(Dispatchers.IO).async {
//                getJeonImage()
//            }.await()
//
//            listAdapter.notifyDataSetChanged()
//        }

        return view
    }

//    private fun getSearchJeonList() {
//        val retrofitAPI = RetrofitConnection.getInstance().create(SearchService::class.java)
//
//        retrofitAPI.getSearchList(
//            0, 10, "city", "전주시"
//        ).enqueue(object : Callback<SearchResponse> {
//            override fun onResponse(
//                call: Call<SearchResponse>,
//                response: Response<SearchResponse>
//            ) {
//                if(response.isSuccessful) {
//                    response.body()?.let { searchList(it) }
//                    Log.d("레트로핏 전주", "전주 업데이트 성공")
//                } else {
//                    Log.d("레트로핏 전주", "전주 업데이트 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//                t.printStackTrace()
//                Log.d("레트로핏 전주", "전주 업데이트 실패 onFailure")
//            }
//        })
//    }
//
//    private fun searchList(resultData : SearchResponse) {
//        val retrofitAPI = RetrofitConnection.getInstance().create(TouristAttractionSingleService::class.java)
//
//        for(it in resultData.data.content) {
//            retrofitAPI.getTouristAttractionsingleData(it.id).enqueue(object : Callback<TouristAttractionSingleResponse> {
//                override fun onResponse(
//                    call: Call<TouristAttractionSingleResponse>,
//                    response: Response<TouristAttractionSingleResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let { getJeonList(it) }
//                        Log.d("레트로핏 전주2", "업데이트 성공")
//                    } else {
//                        Log.d("레트로핏 전주2", "업데이트 실패")
//                    }
//                }
//
//                override fun onFailure(call: Call<TouristAttractionSingleResponse>, t: Throwable) {
//                    t.printStackTrace()
//                    Log.d("레트로핏 전주2", "업데이트 실패 onFailure")
//                }
//            })
//        }
//    }
//
//    private fun getJeonList(list : TouristAttractionSingleResponse) {
//        val data = ListData(list.data.attractionId, isNull(list.data.name), isNull(list.data.address),
//            isNull(list.data.number), isNull(list.data.openingHours), isNull(list.data.breakTime), list.data.hasRamp,
//            list.data.hasToilet, list.data.hasParking, list.data.hasLift, list.data.companionRequired,
//            list.data.hasWheelchair, isNull(list.data.attractionType), list.data.imgId, isNull(list.data.url))
//
//        Log.d("레트로핏 전주3", "${data}")
//
//        datas.add(data)
//    }
//
//    private fun isNull(i : String) : String {
//        if(i == null) {
//            return ""
//        }
//        return i
//    }
//
//    private fun getJeonImage() {
//        val retrofitAPI = RetrofitConnection.getInstance().create(ImageService::class.java)
//
//        for(item in datas) {
//            if (item.imgId != -1) {
//                retrofitAPI.getImage(
//                    item.imgId
//                ).enqueue(object : retrofit2.Callback<ResponseBody> {
//                    override fun onResponse(
//                        call: Call<ResponseBody>,
//                        response: Response<ResponseBody>
//                    ) {
//                        if (response.isSuccessful) {
//                            response.body()?.let {
//                                val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
////                                item.imgBitmap = bitmap
//                            }
//                        } else {
//                            Log.d("레트로핏 전주4", response.toString())
//                            Log.d("레트로핏 전주4", "추천 이미지 업데이트 실패")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        t.printStackTrace()
//                        Log.d("레트로핏 전주4", "추천 이미지 업데이트 실패 onFailure")
//                    }
//                })
//            }
//        }
//    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context)
        binding.rvListJeon.adapter = listAdapter
        binding.rvListJeon.addItemDecoration(VerticalItemDecorator(70))


        listAdapter.datas = jeonDatas
        listAdapter.notifyDataSetChanged()
    }
}