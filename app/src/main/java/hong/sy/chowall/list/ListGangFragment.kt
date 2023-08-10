package hong.sy.chowall.list

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListGangBinding
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGangFragment : Fragment() {
    private var _binding: FragmentListGangBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListGangBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecycler()

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                getSearchGangList()
            }.await()

            CoroutineScope(Dispatchers.IO).async {
                getGangImage()
            }.await()

            listAdapter.notifyDataSetChanged()
        }

        return view
    }

    private fun getSearchGangList() {
        val retrofitAPI = RetrofitConnection.getInstance().create(SearchService::class.java)

        retrofitAPI.getSearchList(
            0, 10, "city", "강릉시"
        ).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let { searchList(it) }
                    Log.d("레트로핏 강릉", "강릉 업데이트 성공")
                } else {
                    Log.d("레트로핏 강릉", "강릉 업데이트 실패")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                Log.d("레트로핏 강릉", "강릉 업데이트 실패 onFailure")
                Log.d("레트로핏 강릉", t.message.toString())
            }
        })
    }

    private fun searchList(resultData : SearchResponse) {
        val retrofitAPI = RetrofitConnection.getInstance().create(TouristAttractionSingleService::class.java)

        for(it in resultData.data.content) {
            retrofitAPI.getTouristAttractionsingleData(it.id).enqueue(object : Callback<TouristAttractionSingleResponse> {
                override fun onResponse(
                    call: Call<TouristAttractionSingleResponse>,
                    response: Response<TouristAttractionSingleResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { getGangList(it) }
                        Log.d("레트로핏 강릉2", "춘천2 업데이트 성공")
                    } else {
                        Log.d("레트로핏 강릉2", "춘천2 업데이트 실패")
                    }
                }

                override fun onFailure(call: Call<TouristAttractionSingleResponse>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("레트로핏 강릉2", "춘천2 업데이트 실패 onFailure")
                }
            })
        }
    }

    private fun getGangList(list : TouristAttractionSingleResponse) {
        val data = ListData(list.data.attractionId, isNull(list.data.name), isNull(list.data.address),
            isNull(list.data.number), isNull(list.data.openingHours), isNull(list.data.breakTime), list.data.hasRamp,
            list.data.hasToilet, list.data.hasParking, list.data.hasLift, list.data.companionRequired,
            list.data.hasWheelchair, isNull(list.data.attractionType), list.data.imgId, isNull(list.data.url))

        Log.d("레트로핏 강릉3", "${data}")

        datas.add(data)
    }

    private fun isNull(i : String) : String {
        if(i == null) {
            return ""
        }
        return i
    }

    private fun getGangImage() {
        val retrofitAPI = RetrofitConnection.getInstance().create(ImageService::class.java)

        for(item in datas) {
            if (item.imgId != -1) {
                retrofitAPI.getImage(
                    item.imgId
                ).enqueue(object : retrofit2.Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                                item.imgBitmap = bitmap
                            }
                        } else {
                            Log.d("레트로핏 강릉4", response.toString())
                            Log.d("레트로핏 강릉4", "추천 이미지 업데이트 실패")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        t.printStackTrace()
                        Log.d("레트로핏 강릉4", "추천 이미지 업데이트 실패 onFailure")
                    }
                })
            }
        }
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context)
        binding.rvListGang.adapter = listAdapter
        binding.rvListGang.addItemDecoration(VerticalItemDecorator(70))


        listAdapter.datas = datas
        listAdapter.notifyDataSetChanged()
    }
}