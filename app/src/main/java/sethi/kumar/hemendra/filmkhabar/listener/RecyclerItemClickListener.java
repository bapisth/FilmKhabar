package sethi.kumar.hemendra.filmkhabar.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by BAPI1 on 02-01-2016.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private final String TAG = "RecyclerClickListener";
    private OnListItemClickListener listener;
    private GestureDetector gestureDetector;

    public RecyclerItemClickListener(Context context, final OnListItemClickListener listener, final RecyclerView recyclerView) {
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                Log.d(TAG, "onSingleTapUp: ");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Log.d(TAG, "onLongPress: ");
                View childView = (View) recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView!=null && listener != null){
                    listener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
                }
                super.onLongPress(e);
            }
        });
    }

    public interface OnListItemClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = (View) rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && listener!=null && gestureDetector.onTouchEvent(e)){
            listener.onItemClick(childView, rv.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}
