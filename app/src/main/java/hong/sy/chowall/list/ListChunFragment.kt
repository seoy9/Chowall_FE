package hong.sy.chowall.list

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hong.sy.chowall.R
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListChunBinding
import hong.sy.chowall.recommend.Recommend_Result
import hong.sy.chowall.recommend.ResultData
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.io.ByteArrayOutputStream

class ListChunFragment(data: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListChunBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()
    val chunDatas = data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListChunBinding.inflate(inflater, container, false)
        val view = binding.root

        listAdapter = ListRecyclerAdapter(binding.root.context, chunDatas)
        binding.rvListChun.adapter = listAdapter
        binding.rvListChun.addItemDecoration(VerticalItemDecorator(70))

//        listAdapter.datas = chunDatas
//        listAdapter.notifyDataSetChanged()

        Log.d("레트로핏 춘천", "${listAdapter.datas}")
        Log.d("레트로핏 춘천", "${listAdapter.itemCount}")

        return view
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context, chunDatas)
        binding.rvListChun.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListChun.adapter = listAdapter
        binding.rvListChun.addItemDecoration(VerticalItemDecorator(70))

        listAdapter.datas = chunDatas
        listAdapter.notifyDataSetChanged()
    }
}