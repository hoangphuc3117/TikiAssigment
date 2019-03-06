package ppapps.tikiexercise.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class HorizontalSpaceItemDecoration(horizontalSpaceSize: Int) : RecyclerView.ItemDecoration() {
    private var mHorizontalSpaceSize: Int = horizontalSpaceSize

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // first item: left = horizontalSpaceSize, right = horizontalSpaceSize/2
//        if (parent.getChildAdapterPosition(view) == 0) {
//            outRect.left = mHorizontalSpaceSize
//            outRect.right = mHorizontalSpaceSize / 2
//            return
//        }

        // last item: left = horizontalSpaceSize /2, right = horizontalSpaceSize
        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
//            outRect.left = mHorizontalSpaceSize / 2
//            outRect.right = mHorizontalSpaceSize
            return
        }

        // rest items: left = horizontalSpaceSize /2, right = horizontalSpaceSize/2
        outRect.bottom = mHorizontalSpaceSize
    }
}