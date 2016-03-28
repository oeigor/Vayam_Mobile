package com.objectedge.store.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.objectedge.store.R;

/**
 * Created by eloor_000 on 3/27/2015.
 */
public class LeftMenuItemView extends LinearLayout {

    private TextView textView;

    public LeftMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LeftMenuItemView(Context context) {
        super(context);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.item_left_menu, this);
        textView = (TextView) findViewById(R.id.item_left_menu_text);
    }

    public void setText(String text){
        textView.setText(text);
    }

}
