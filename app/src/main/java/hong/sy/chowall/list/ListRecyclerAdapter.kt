package hong.sy.chowall.list

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hong.sy.chowall.R
import hong.sy.chowall.retrofit.SearchResponse
import hong.sy.chowall.retrofit.TouristAttractionSingleResponse

class ListRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder>() {

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
        private val imgAddress: ImageView = itemView.findViewById(R.id.img_list_address)
        private val tvAddress: TextView = itemView.findViewById(R.id.tv_list_address)
        private val imgPhone: ImageView = itemView.findViewById(R.id.img_list_phone)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_list_phone)
        private val imgTime: ImageView = itemView.findViewById(R.id.img_list_time)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_list_time)
        private val tvBreakTime: TextView = itemView.findViewById(R.id.tv_list_break_time)
        private val imgIcon1: ImageView = itemView.findViewById(R.id.img_list_icon_1)
        private val imgIcon2: ImageView = itemView.findViewById(R.id.img_list_icon_2)
        private val imgIcon3: ImageView = itemView.findViewById(R.id.img_list_icon_3)
        private val imgIcon4: ImageView = itemView.findViewById(R.id.img_list_icon_4)
        private val imgIcon5: ImageView = itemView.findViewById(R.id.img_list_icon_5)
        private var imgIconList = arrayListOf<Boolean>(false, false, false, false, false)

        fun bind(item: ListData) {
            Glide.with(itemView)
                .load(item.imgBitmap)
                .error(R.drawable.img_basic_list)
                .fallback(R.drawable.img_basic_list)
                .into(imgList)

            tvName.text = item.name

            if (item.address == "") {
                imgAddress.visibility = View.INVISIBLE
            }
            tvAddress.text = item.address

            if (item.number == "") {
                imgPhone.visibility = View.INVISIBLE
            }
            tvPhone.text = item.number

            if (item.openingHours == "") {
                imgTime.visibility = View.INVISIBLE
            }
            tvTime.text = item.openingHours
            tvBreakTime.text = item.breakTime

            setIcon(item.hasRamp, R.drawable.icon_runway)
            setIcon(item.hasToilet, R.drawable.icon_restroom)
            setIcon(item.hasParking, R.drawable.icon_parking)
            setIcon(item.hasLift, R.drawable.icon_lift)
            setIcon(item.hasWheelchair, R.drawable.icon_rental)
            setIcon(item.companionRequired, R.drawable.icon_companion)

            checkEmpty()
        }

        fun setIcon(isHave: Boolean, resource: Int) {
            if (isHave) {
                for (i in 0..imgIconList.size - 1) {
                    if (imgIconList[i] == false) {
                        when (i) {
                            0 -> {
                                imgIcon1.setImageResource(resource)
                                imgIconList[0] = true
                                return
                            }
                            1 -> {
                                imgIcon2.setImageResource(resource)
                                imgIconList[1] = true
                                return
                            }
                            2 -> {
                                imgIcon3.setImageResource(resource)
                                imgIconList[2] = true
                                return
                            }
                            3 -> {
                                imgIcon4.setImageResource(resource)
                                imgIconList[3] = true
                                return
                            }
                            4 -> {
                                imgIcon5.setImageResource(resource)
                                imgIconList[4] = true
                                return
                            }
                        }
                    }
                }
            }
        }

        fun checkEmpty() {
            for (i in 0..imgIconList.size - 1) {
                if (imgIconList[i] == false) {
                    when (i) {
                        0 -> {
                            imgIcon1.visibility = View.INVISIBLE
                        }
                        1 -> {
                            imgIcon2.visibility = View.INVISIBLE
                        }
                        2 -> {
                            imgIcon3.visibility = View.INVISIBLE
                        }
                        3 -> {
                            imgIcon4.visibility = View.INVISIBLE
                        }
                        4 -> {
                            imgIcon5.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }
    }
}