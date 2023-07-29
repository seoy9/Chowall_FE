package hong.sy.chowall.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hong.sy.chowall.R
import org.w3c.dom.Text

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var datas = mutableListOf<ListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgList: ImageView = itemView.findViewById(R.id.img_list)
        private val tvName: TextView = itemView.findViewById(R.id.tv_list_name)
        private val tvAddress: TextView = itemView.findViewById(R.id.tv_list_address)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_list_phone)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_list_time)
        private val tvBreakTime: TextView = itemView.findViewById(R.id.tv_list_break_time)

        fun bind(item: ListData) {
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