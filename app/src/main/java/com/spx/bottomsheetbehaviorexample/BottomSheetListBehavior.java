package com.spx.bottomsheetbehaviorexample;


import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BottomSheetListBehavior extends BottomSheetBehavior {
    private static final String TAG = "BottomSheetListBehavior";

    private boolean anchorTouched = false;
    private RecyclerView recyclerView;

    public BottomSheetListBehavior() {
    }

    public BottomSheetListBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed, int type) {
        if (dy < 0 && leaveEventToNestingChild(target)) {
            Log.d(TAG, "let child have event!");
            return;
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    private boolean leaveEventToNestingChild(View target) {
        return !(isAnchorTouched() || isChildOnTop((ViewGroup) target));
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            setAnchorTouched(false);
        }
        return super.onInterceptTouchEvent(parent, child, event);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        setAnchorTouched(false);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }


    private boolean isAnchorTouched() {
        return anchorTouched;
    }

    public void setAnchorTouched(boolean flag) {
        anchorTouched = flag;
    }


    private boolean isChildOnTop(ViewGroup target) {
        if (target == null) {
            return false;
        }

        if (recyclerView == null) {
            View view = target.findViewById(R.id.recyclerView);
            if (view == null || !(view instanceof RecyclerView)) {
                throw new RuntimeException("must have a RecyclerView with id[recyclerView] in NestedScrollView!");
            }
            recyclerView = (RecyclerView) view;
        }


        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int firstPos = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            if (firstPos == 0) {
                return true;
            }
        } else {
            throw new RuntimeException("must have a RecyclerView with LinearLayoutManager in NestedScrollView!");
        }
        return false;
    }


    public void setAnchorView(View anchorView) {
        anchorView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setAnchorTouched(true);
                } else {
                    setAnchorTouched(false);
                }
                return false;
            }
        });
    }
}
