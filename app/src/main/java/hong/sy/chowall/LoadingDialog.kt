package hong.sy.chowall

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import hong.sy.chowall.R

class LoadingDialog (context: Context) : Dialog(context) {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.dialog_loading)
//
//        // 취소 불가능
//        setCancelable(false)
//
//        // 배경 투명하게 바꿔줌
//        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//    }

        init {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
//            setCanceledOnTouchOutside(false)

            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
}