package com.mage.nestscrollelm.behovior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.mage.nestscrollelm.R;

/**
 * author  :mayong
 * function:第一个控件跟随手指Y轴移动，第二个控件做正弦曲线运动
 * date    :2020/12/13
 **/
class DependOnBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private int heightPixels;
    float degree = (float) (Math.PI * 4);

    public DependOnBehavior() {

    }

    public DependOnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        heightPixels = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        return dependency.getId() == R.id.iv1;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull ImageView child, @NonNull View dependency) {
        float dependWidth = dependency.getWidth();
        float perDegree = degree / heightPixels;//计算每一像素的距离对应的角度
        float sinX = (float) (dependWidth * Math.sin(perDegree * dependency.getY()));//在X方向上的Sin值
        System.out.println("sinX:" + sinX + "degree:" + perDegree * dependency.getY());
        float offsetX = (float) (dependency.getX() + 2*dependWidth + sinX);//计算依赖控件的Y值
        child.setX(offsetX);
        child.setY(dependency.getY());
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
