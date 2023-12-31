package com.ddwu.chowall.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddwu.chowall.VerticalItemDecorator
import com.ddwu.chowall.databinding.FragmentListJeonBinding

class ListJeonFragment(data: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListJeonBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
    val jeonDatas = data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListJeonBinding.inflate(inflater, container, false)
        val view = binding.root

        listAdapter = ListRecyclerAdapter(binding.root.context, jeonDatas)
        binding.rvListJeon.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvListJeon.adapter = listAdapter
        binding.rvListJeon.addItemDecoration(VerticalItemDecorator(70))

        return view
    }
}