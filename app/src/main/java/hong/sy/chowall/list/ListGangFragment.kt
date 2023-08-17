package hong.sy.chowall.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hong.sy.chowall.VerticalItemDecorator
import hong.sy.chowall.databinding.FragmentListGangBinding

class ListGangFragment(datas: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListGangBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val gangDatas = datas

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListGangBinding.inflate(inflater, container, false)
        val view = binding.root

        listAdapter = ListRecyclerAdapter(binding.root.context, gangDatas)
        binding.rvListGang.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListGang.adapter = listAdapter
        binding.rvListGang.addItemDecoration(VerticalItemDecorator(70))

        return view
    }
}