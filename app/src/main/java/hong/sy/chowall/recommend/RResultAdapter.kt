package hong.sy.chowall.recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.RResultItemBinding

class RResultAdapter(private var recommendResultList: ArrayList<RResult>, var context: Context)
    : RecyclerView.Adapter<RResultAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return recommendResultList.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendResultList[position])
    }

    inner class ViewHolder(private val binding: RResultItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: RResult) {
            val resourceImgId = context.resources.getIdentifier(result.img, "drawable", context.packageName)
            val resourceTitle = context.resources.getIdentifier(result.title, "string", context.packageName)
            val resourceC1 = context.resources.getIdentifier(result.c1, "string", context.packageName)
            val resourceC2 = context.resources.getIdentifier(result.c2, "string", context.packageName)
            val resourceC3 = context.resources.getIdentifier(result.c3, "string", context.packageName)
            val resourceC4 = context.resources.getIdentifier(result.c4, "string", context.packageName)

            binding.imgReResultItem.setImageResource(resourceImgId)

            var title = context.resources.getString(resourceTitle)

            if(title.length > 16) {
                title = title.slice(0..13) + "..."
            }

            binding.tvTitleReResultItem.text = title

            var c1 = context.resources.getString(resourceC1)
            var c2 = context.resources.getString(resourceC2)
            var c3 = context.resources.getString(resourceC3)
            var c4 = context.resources.getString(resourceC4)

            if(c1.length > 7) {
                c1 = c1.slice(0..5) + "..."
            }
            if(c2.length > 7) {
                c2 = c2.slice(0..5) + "..."
            }
            if(c3.length > 7) {
                c3 = c3.slice(0..5) + "..."
            }
            if(c4.length > 7) {
                c4 = c4.slice(0..5) + "..."
            }

            binding.tvC1ReResultItem.text = c1
            binding.tvC2ReResultItem.text = c2
            binding.tvC3ReResultItem.text = c3
            binding.tvC4ReResultItem.text = c4

            val screenHeight = context.resources.displayMetrics.heightPixels
//            binding.layoutReResultItem.layoutParams.height = (screenWidth * 0.27).toInt()
            binding.layoutReResultItem.layoutParams.height = (screenHeight * 0.235).toInt()
        }
    }
}