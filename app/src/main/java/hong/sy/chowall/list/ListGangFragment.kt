package hong.sy.chowall.list

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListGangBinding
import hong.sy.chowall.retrofit.*
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGangFragment(datas: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListGangBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val datas = mutableListOf<ListData>()
    val gangDatas = datas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListGangBinding.inflate(inflater, container, false)
        val view = binding.root

//        initRecycler()

        listAdapter = ListRecyclerAdapter(binding.root.context, gangDatas)
        binding.rvListGang.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListGang.adapter = listAdapter
        binding.rvListGang.addItemDecoration(VerticalItemDecorator(70))

//        listAdapter.datas = gangDatas
//        listAdapter.notifyDataSetChanged()

        Log.d("레트로핏 강릉", "${listAdapter.datas}")
        Log.d("레트로핏 강릉", "${listAdapter.itemCount}")


        return view
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context, gangDatas)
        binding.rvListGang.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListGang.adapter = listAdapter
        binding.rvListGang.addItemDecoration(VerticalItemDecorator(70))


        listAdapter.datas = gangDatas
        listAdapter.notifyDataSetChanged()
    }
}