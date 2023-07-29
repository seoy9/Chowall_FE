package hong.sy.chowall.recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hong.sy.chowall.R

class ResultRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ResultRecyclerAdapter.ViewHolder>() {

    var datas = mutableListOf<ResultData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.result_item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgList: ImageView = itemView.findViewById(R.id.img_result)
        private val tvName: TextView = itemView.findViewById(R.id.tv_result_name)
        private val tvAddress: TextView = itemView.findViewById(R.id.tv_result_address)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_result_phone)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_result_time)
        private val tvBreakTime: TextView = itemView.findViewById(R.id.tv_result_break_time)

        fun bind(item: ResultData) {
            Glide.with(itemView).load(item.img).into(imgList)
//            imgList.setImageResource(item.img)
            tvName.text = item.name
            tvAddress.text = item.address
            tvPhone.text = item.phone
            tvTime.text = item.time
            tvBreakTime.text = item.breakTime
        }
    }
}