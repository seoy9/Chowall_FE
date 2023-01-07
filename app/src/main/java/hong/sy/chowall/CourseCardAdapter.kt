package hong.sy.chowall

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.CourseCardItemBinding


class CourseCardAdapter (private var courseCardList: ArrayList<CourseCard>, var context: Context)
    : RecyclerView.Adapter<CourseCardAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return courseCardList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CourseCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(courseCardList[position])
    }

    inner class ViewHolder(private val binding: CourseCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CourseCard) {
            val resourceImgId = context.resources.getIdentifier(card.img, "drawable", context.packageName)
            val resourceDescId = context.resources.getIdentifier(card.desc, "string", context.packageName)
            binding.imgCourseCard.setImageResource(resourceImgId)
            binding.courseCardDesc.text = context.resources.getString(resourceDescId)
        }
    }
}