package hong.sy.chowall

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hong.sy.chowall.databinding.MainCourseCardBinding

class CourseCardAdapter (
    val courseCardList: ArrayList<CourseCard>,
    var context: Context)
    : RecyclerView.Adapter<CourseCardAdapter.AdapterViewHolder>() {

    override fun getItemCount(): Int {
        return courseCardList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding = MainCourseCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(courseCardList[position])
    }

    inner class AdapterViewHolder(private val binding: MainCourseCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(courseCard: CourseCard) {
            val resourceImgId = context.resources.getIdentifier(courseCard.img, "drawable", context.packageName)
            val resourceDescId = context.resources.getIdentifier(courseCard.desc, "string", context.packageName)
            binding.imgCourseCard.setImageResource(resourceImgId)
            binding.courseCardDesc.text = context.resources.getString(resourceDescId)
        }
    }
}