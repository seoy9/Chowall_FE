package hong.sy.chowall.recommend

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hong.sy.chowall.R
import hong.sy.chowall.retrofit.ImageService
import hong.sy.chowall.retrofit.RetrofitConnection
import hong.sy.chowall.retrofit.SearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class ResultRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ResultRecyclerAdapter.ViewHolder>() {

    var datas = arrayListOf<ResultData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.result_item_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val imgResult: ImageView = itemView.findViewById(R.id.img_result)
        private val tvName: TextView = itemView.findViewById(R.id.tv_result_name)
        private val imgAddress: ImageView = itemView.findViewById(R.id.img_result_address)
        private val tvAddress: TextView = itemView.findViewById(R.id.tv_result_address)
        private val imgPhone: ImageView = itemView.findViewById(R.id.img_result_phone)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_result_phone)
        private val imgTime: ImageView = itemView.findViewById(R.id.img_result_time)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_result_time)
        private val tvBreakTime: TextView = itemView.findViewById(R.id.tv_result_break_time)
        private val imgResultWhere: ImageView = itemView.findViewById(R.id.img_result_where)
        private val imgIcon1: ImageView = itemView.findViewById(R.id.img_result_icon_1)
        private val imgIcon2: ImageView = itemView.findViewById(R.id.img_result_icon_2)
        private val imgIcon3: ImageView = itemView.findViewById(R.id.img_result_icon_3)
        private val imgIcon4: ImageView = itemView.findViewById(R.id.img_result_icon_4)
        private val imgIcon5: ImageView = itemView.findViewById(R.id.img_result_icon_5)
        private var imgIconList = arrayListOf<Boolean>(false, false, false, false, false)

        fun bind(item: ResultData) {
            Glide.with(itemView)
                .load(Uri.parse(item.imgUri))
                .error(R.drawable.img_basic_list)
                .fallback(R.drawable.img_basic_list)
                .into(imgResult)

            tvName.text = item.name

            if(item.address == "") {
                imgAddress.visibility = View.INVISIBLE
            }
            tvAddress.text = item.address

            if(item.number == "") {
                imgPhone.visibility = View.INVISIBLE
            }
            tvPhone.text = item.number

            if(item.openingHours == "") {
                imgTime.visibility = View.INVISIBLE
            }
            tvTime.text = item.openingHours
            tvBreakTime.text = item.breakTime

            when(item.attractionType) {
                "식당" -> {
                    imgResultWhere.setImageResource(R.drawable.icon_restaurant)
                }
                "관광지" -> {
                    imgResultWhere.setImageResource(R.drawable.icon_tour)
                }
                "카페" -> {
                    imgResultWhere.setImageResource(R.drawable.icon_cafe)
                }
                "숙소" -> {
                    imgResultWhere.setImageResource(R.drawable.icon_lodging)
                }
            }

            setIcon(item.hasRamp, R.drawable.icon_runway)
            setIcon(item.hasToilet, R.drawable.icon_restroom)
            setIcon(item.hasParking, R.drawable.icon_parking)
            setIcon(item.hasLift, R.drawable.icon_lift)
            setIcon(item.hasWheelchair, R.drawable.icon_rental)
            setIcon(item.companionRequired, R.drawable.icon_companion)

            checkEmpty()
        }

        fun setIcon(isHave: Boolean, resource: Int) {
            if(isHave) {
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
            for(i in 0..imgIconList.size-1) {
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