package hong.sy.chowall

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.ListItemBinding


class CourseCardAdapter (private var courseCardList: ArrayList<CourseCard>, var context: Context)
    : RecyclerView.Adapter<CourseCardAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return courseCardList.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseCardList[position])
    }

    inner class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CourseCard) {
            val resourceImgId = context.resources.getIdentifier(card.img, "drawable", context.packageName)
            val resourceDescId = context.resources.getIdentifier(card.desc, "string", context.packageName)

            binding.imgCourseItem.setImageResource(resourceImgId)
            binding.tvCourseTitleItem.text = context.resources.getString(resourceDescId)

            val screenWidth = context.resources.displayMetrics.heightPixels
            // scalableLayout 사용 시
//            binding.imageView.layoutParams.width = (screenWidth * 0.163872828).toInt()
//            binding.imageView.layoutParams.height = (screenWidth * 0.16127168).toInt()

            // scalableLayout 미사용 시
            binding.imageView.layoutParams.width = (screenWidth * 0.1556791866).toInt()
            binding.imageView.layoutParams.height = (screenWidth * 0.153208096).toInt()
        }
    }
}