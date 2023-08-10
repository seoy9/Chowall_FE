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
import hong.sy.chowall.databinding.FragmentRecommendQ1Binding

class RecommendQ1Fragment : Fragment() {
    private var _binding: FragmentRecommendQ1Binding? = null
    private lateinit var content: TextView
    private val binding get() = _binding!!
    private var city = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendQ1Binding.inflate(inflater, container, false)
        val view = binding.root

        setContentColor()
        setButton()

        return view
    }

    private fun setContentColor() {
        content = binding.tvRecQ1
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(resources.getColor(R.color.main))
        builder.setSpan(colorMainSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun setButton() {
        binding.btnRecQ1Chun.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnRecQ1Gang.isChecked = false
                binding.btnRecQ1Jeon.isChecked = false
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ1Next.isSelected = true
                city = "춘천시"
            } else {
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ1Next.isSelected = false
                city = ""
            }
        }

        binding.btnRecQ1Gang.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnRecQ1Chun.isChecked = false
                binding.btnRecQ1Jeon.isChecked = false
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ1Next.isSelected = true
                city = "강릉시"
            } else {
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ1Next.isSelected = false
                city = ""
            }
        }

        binding.btnRecQ1Jeon.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                binding.btnRecQ1Chun.isChecked = false
                binding.btnRecQ1Gang.isChecked = false
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_next)
                binding.btnRecQ1Next.isSelected = true
                city = "전주시"
            } else {
                binding.btnRecQ1Next.setImageResource(R.drawable.btn_rec_non_next)
                binding.btnRecQ1Next.isSelected = false
                city = ""
            }
        }

        binding.btnRecQ1Next.setOnClickListener {
            if(binding.btnRecQ1Next.isSelected) {
                val rActivity = activity as RecommendActivity
                rActivity.changeFragment(1, city)
            }
        }
    }
}