package com.mage.nestscrollelm.behovior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author  :mayong
 * function:
 * date    :2020/12/13
 **/
public class CustomNestBehavior extends CoordinatorLayout.Behavior<View> {
    private String TAG = "behavior";
    private int childTransY;
    public static boolean isHeaderFollowRecycle;

    public CustomNestBehavior() {
    }

    public CustomNestBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return target instanceof RecyclerView;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        RecyclerView recyclerView = (RecyclerView) target;
        float transY = child.getTranslationY();
        int offset = recyclerView.computeVerticalScrollOffset();
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();


        if (dy > 0) {//向上
            if (offset > child.getHeight()) {
                if (child.getTranslationY() < -child.getHeight()) {//如果child的便宜小于-child.height，那么设置便宜量为-child.getHeight()
                    setTransOffset(child, target, -child.getHeight());
                }
            } else {
                scroll(child, target, dy);
            }
        } else {//向下
            if (isHeaderFollowRecycle) {//是否recycle向下滑动，header也就是child马上跟随向下滑动
                toDownScroll(transY, child, target, dy);
            } else {
                if (offset == 0) {//只有recycle滑到顶的时候child才能向下滑动，否自child一直是隐藏状态不能出来
                    toDownScroll(transY, child, target, dy);
                }
            }
        }
    }

    private void toDownScroll(float transY, View child, View target, int dy) {
        if (transY < 0) {//只有child的translationY小于0，打于-height才滑动
            scroll(child, target, dy);
        } else if (transY > 0) {//避免向下滑动的时候，child和target向下滑动超出距离
            transY = 0;
            setTransOffset(child, target, transY);
        }
    }

    private void scroll(View child, View target, int dy) {
        int transY = 0;
        if (child.getTranslationY() < -child.getHeight()) {
            transY = -child.getHeight();
        } else if (child.getTranslationY() > 0) {
            transY = 0;
        } else {
            transY = (int) (child.getTranslationY() - dy);
        }
        setTransOffset(child, target, transY);
    }

    private void setTransOffset(View child, View target, float offsetY) {
        child.setTranslationY(offsetY);
        target.setTranslationY(offsetY + child.getHeight());
    }
}