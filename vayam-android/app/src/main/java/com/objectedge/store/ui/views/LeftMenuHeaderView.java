package com.objectedge.store.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.objectedge.store.R;
import com.objectedge.store.utils.ColorUtil;

/**
 * Created by eloor_000 on 3/27/2015.
 */
public class LeftMenuHeaderView extends RelativeLayout {

    public final static int HIGHLIGHT_MODE = 1;
    public final static int HEADER_MODE = 2;

    private TextView textView;
    private View dividerView;
    private int mode = HEADER_MODE;

    public LeftMenuHeaderView(Context context) {
        super(context);
        initView();
    }

    public LeftMenuHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.header_left_menu, this);
        textView = (TextView) findViewById(R.id.header_left_menu_text);
        dividerView = findViewById(R.id.header_left_menu_divider);
        dividerView.setVisibility(GONE);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setHighlightMode(){
        mode = HIGHLIGHT_MODE;
        setBackgroundColor(ColorUtil.getColor(getContext(),R.color.orange));
        textView.setTextColor(ColorUtil.getColor(getContext(), R.color.white));
    }

    public void setHeaderMode(){
        mode = HEADER_MODE;
        setBackgroundColor(ColorUtil.getColor(getContext(), R.color.white));
        textView.setTextColor(ColorUtil.getColor(getContext(), R.color.blue));
    }

    public void setDividerVisibility(int visibility){
        dividerView.setVisibility(visibility);
    }

    public int getMode(){
        return mode;
    }
}
