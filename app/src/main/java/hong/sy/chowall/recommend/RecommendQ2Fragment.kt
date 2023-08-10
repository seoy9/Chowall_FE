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
import hong.sy.chowall.databinding.FragmentRecommendQ2Binding

class RecommendQ2Fragment : Fragment() {
    private var _binding: FragmentRecommendQ2Binding? = null
    private lateinit var content: TextView
    private val binding get() = _binding!!
    private var companion = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendQ2Binding.inflate(inflater, container, false)
        val view = binding.root

        setContentColor()
        setButton()

        return view
    }

    private fun setContentColor() {
        content = binding.tvRecQ2
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setButton() {
        binding.btnRecQ2Alone.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ2Family.isChecked = false
                binding.btnRecQ2Friend.isChecked = false
                binding.btnRecQ2Lover.isChecked = false
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ2Next.isSelected = true
                companion = 1
            } else {
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ2Next.isSelected = false
                companion = 0
            }
        }

        binding.btnRecQ2Family.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ2Alone.isChecked = false
                binding.btnRecQ2Friend.isChecked = false
                binding.btnRecQ2Lover.isChecked = false
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ2Next.isSelected = true
                companion = 2
            } else {
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ2Next.isSelected = false
                companion = 0
            }
        }

        binding.btnRecQ2Friend.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ2Alone.isChecked = false
                binding.btnRecQ2Family.isChecked = false
                binding.btnRecQ2Lover.isChecked = false
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ2Next.isSelected = true
                companion = 3
            } else {
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ2Next.isSelected = false
                companion = 0
            }
        }

        binding.btnRecQ2Lover.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked) {
                binding.btnRecQ2Alone.isChecked = false
                binding.btnRecQ2Family.isChecked = false
                binding.btnRecQ2Friend.isChecked = false
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ2Next.isSelected = true
                companion = 4
            } else {
                binding.btnRecQ2Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ2Next.isSelected = false
                companion = 0
            }
        }

        binding.btnRecQ2Next.setOnClickListener {
            if(binding.btnRecQ2Next.isSelected) {
                val rActivity = activity as RecommendActivity
                rActivity.changeFragment(2, companion)
            }
        }
    }
}