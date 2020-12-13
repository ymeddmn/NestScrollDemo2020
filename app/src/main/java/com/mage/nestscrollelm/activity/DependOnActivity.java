package com.mage.nestscrollelm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.mage.nestscrollelm.R;

/**
 * 实现一个控件跟随另一个控件滑动的效果
 */
public class DependOnActivity extends AppCompatActivity {

    private ImageView iv2, iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depend_on);
        iv2 = findViewById(R.id.iv2);
        iv1 = findViewById(R.id.iv1);
    }

    float lastY = 0;
    float movedY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                movedY = event.getY() - lastY;
                iv1.setY(iv1.getY() + movedY);
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}