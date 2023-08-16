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

//        initRecycler()

        listAdapter = ListRecyclerAdapter(binding.root.context, jeonDatas)
        binding.rvListJeon.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListJeon.adapter = listAdapter
        binding.rvListJeon.addItemDecoration(VerticalItemDecorator(70))

//        listAdapter.datas = jeonDatas
//        listAdapter.notifyDataSetChanged()

        Log.d("레트로핏 전주", "${listAdapter.datas}")
        Log.d("레트로핏 전주", "${listAdapter.itemCount}")

        return view
    }

    private fun initRecycler() {
        listAdapter = ListRecyclerAdapter(binding.root.context, jeonDatas)
        binding.rvListJeon.adapter = listAdapter
        binding.rvListJeon.addItemDecoration(VerticalItemDecorator(70))


        listAdapter.datas = jeonDatas
        listAdapter.notifyDataSetChanged()
    }
}