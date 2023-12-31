package com.ddwu.chowall.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddwu.chowall.VerticalItemDecorator
import com.ddwu.chowall.databinding.FragmentListChunBinding

class ListChunFragment(data: ArrayList<ListData>) : Fragment() {
    private var _binding: FragmentListChunBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: ListRecyclerAdapter
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

        Log.d("레트로핏 춘천", "${listAdapter.datas}")
        Log.d("레트로핏 춘천", "${listAdapter.itemCount}")

        return view
    }
}