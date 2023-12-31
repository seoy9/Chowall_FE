package com.ddwu.chowall.recommend

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ddwu.chowall.R
import com.ddwu.chowall.databinding.FragmentRecommendQ4Binding

class RecommendQ4Fragment : Fragment() {
    private var _binding: FragmentRecommendQ4Binding? = null
    private lateinit var content: TextView
    private val binding get() = _binding!!
    private var type = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendQ4Binding.inflate(inflater, container, false)
        val view = binding.root

        setContentColor()
        setButton()

        return view
    }

    private fun setContentColor() {
        content = binding.tvRecQ4
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setButton() {
        binding.btnRecQ4Refresh.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ4Food.isChecked = false
                binding.btnRecQ4Hot.isChecked = false
                binding.btnRecQ4Activity.isChecked = false
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_result)
                binding.btnRecQ4Result.isSelected = true
                type = 1
            } else {
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_non_result)
                binding.btnRecQ4Result.isSelected = false
                type = 0
            }
        }

        binding.btnRecQ4Food.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ4Refresh.isChecked = false
                binding.btnRecQ4Hot.isChecked = false
                binding.btnRecQ4Activity.isChecked = false
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_result)
                binding.btnRecQ4Result.isSelected = true
                type = 2
            } else {
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_non_result)
                binding.btnRecQ4Result.isSelected = false
                type = 0
            }
        }

        binding.btnRecQ4Hot.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ4Refresh.isChecked = false
                binding.btnRecQ4Food.isChecked = false
                binding.btnRecQ4Activity.isChecked = false
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_result)
                binding.btnRecQ4Result.isSelected = true
                type = 3
            } else {
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_non_result)
                binding.btnRecQ4Result.isSelected = false
                type = 0
            }
        }

        binding.btnRecQ4Activity.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ4Refresh.isChecked = false
                binding.btnRecQ4Food.isChecked = false
                binding.btnRecQ4Hot.isChecked = false
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_result)
                binding.btnRecQ4Result.isSelected = true
                type = 4
            } else {
                binding.btnRecQ4Result.setImageResource(R.drawable.btn_rec_non_result)
                binding.btnRecQ4Result.isSelected = false
                type = 0
            }
        }

        binding.btnRecQ4Result.setOnClickListener {
            if(binding.btnRecQ4Result.isSelected) {
                val rActivity = activity as RecommendActivity
                rActivity.changeFragment(4, type)
            }
        }
    }
}