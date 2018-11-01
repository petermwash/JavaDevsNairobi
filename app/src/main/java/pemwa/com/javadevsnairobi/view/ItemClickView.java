package pemwa.com.javadevsnairobi.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ItemClickView implements RecyclerView.OnItemTouchListener {

    private onItemClickListener click;

    public interface onItemClickListener{

        void onItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public ItemClickView(Context context, onItemClickListener click) {
        this.click = click;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View v = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (v != null && click != null && gestureDetector.onTouchEvent(motionEvent)) {
            click.onItemClick(v, recyclerView.getChildPosition(v));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
