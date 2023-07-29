package hong.sy.chowall

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
//        outRect.top = divHeight
//        outRect.bottom = divHeight

        val position = parent.getChildAdapterPosition(view)

        if(position == state.itemCount-1) {
            outRect.top = divHeight
            outRect.bottom = divHeight
        } else {
            outRect.top = divHeight
        }
    }
}