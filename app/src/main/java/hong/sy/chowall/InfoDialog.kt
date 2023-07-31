package hong.sy.chowall

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.widget.TextView
import hong.sy.chowall.databinding.DialogPicInfoBinding

class InfoDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogPicInfoBinding

    override fun onCreate(savedInstances: Bundle?) {
        super.onCreate(savedInstances)

        binding = DialogPicInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        changeColor(binding.tvInfoRunway, 5, 8)
        changeColor(binding.tvInfoRestroom, 0, 7)
        changeColor(binding.tvInfoParking, 0, 7)
        changeColor(binding.tvInfoLift, 0, 5)
        changeColor(binding.tvInfoRental, 0, 6)

        changeSizeColor(binding.tvInfoCompanion)
    }

    private fun initViews() = with(binding) {
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnInfoClose.setOnClickListener {
            dismiss()
        }
    }

    private fun changeColor(content: TextView, start: Int, end: Int) {
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorMainSpan = ForegroundColorSpan(Color.parseColor("#67ABAC"))
        builder.setSpan(colorMainSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }

    private fun changeSizeColor(content: TextView) {
        val textData = content.text.toString()
        val builder = SpannableStringBuilder(textData)
        val sizeSmallSpan = RelativeSizeSpan(0.8f)
        val colorMainSpan = ForegroundColorSpan(Color.parseColor("#67ABAC"))
        builder.setSpan(sizeSmallSpan, 23, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(colorMainSpan, 5, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        content.text = builder
    }
}