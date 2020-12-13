package com.mage.nestscrollelm.view;

import android.view.View;

import com.mage.nestscrollelm.R;

/**
 * author  :mayong
 * function:
 * date    :2020-06-13
 **/
public class ElmNestViews {


    public View title;

    public void initView(ElmNestScrollLayout parent) {
        title = parent.findViewById(R.id.title);
    }
}
