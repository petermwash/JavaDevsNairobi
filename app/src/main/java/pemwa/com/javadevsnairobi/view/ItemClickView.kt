package pemwa.com.javadevsnairobi.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class ItemClickView(context: Context, private val click: onItemClickListener?) : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })

    interface onItemClickListener {

        fun onItemClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
        val v = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
        if (v != null && click != null && gestureDetector.onTouchEvent(motionEvent)) {
            click.onItemClick(v, recyclerView.getChildPosition(v))
        }
        return false
    }

    override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(b: Boolean) {

    }
}
