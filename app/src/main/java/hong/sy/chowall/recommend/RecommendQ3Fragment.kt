package hong.sy.chowall.recommend

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hong.sy.chowall.R
import hong.sy.chowall.databinding.FragmentRecommendQ3Binding

class RecommendQ3Fragment : Fragment() {
    private var _binding: FragmentRecommendQ3Binding? = null
    private lateinit var content: TextView
    private val binding get() = _binding!!
    private var days = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendQ3Binding.inflate(inflater, container, false)
        val view = binding.root

        setContentColor()
        setButton()

        return view
    }

    private fun setContentColor() {
        content = binding.tvRecQ3
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setButton() {
        binding.btnRecQ3Day.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ31night.isChecked = false
                binding.btnRecQ32night.isChecked = false
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ3Next.isSelected = true
                days = 1
            } else {
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ3Next.isSelected = false
                days = 0
            }
        }

        binding.btnRecQ31night.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ3Day.isChecked = false
                binding.btnRecQ32night.isChecked = false
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ3Next.isSelected = true
                days = 2
            } else {
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ3Next.isSelected = false
                days = 0
            }
        }

        binding.btnRecQ32night.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ3Day.isChecked = false
                binding.btnRecQ31night.isChecked = false
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ3Next.isSelected = true
                days = 3
            } else {
                binding.btnRecQ3Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ3Next.isSelected = false
                days = 0
            }
        }

        binding.btnRecQ3Next.setOnClickListener {
            if(binding.btnRecQ3Next.isSelected) {
                val rActivity = activity as RecommendActivity
                rActivity.changeFragment(3, days)
            }
        }
    }
}