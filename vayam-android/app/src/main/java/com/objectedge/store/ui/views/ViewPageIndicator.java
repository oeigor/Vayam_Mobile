package com.objectedge.store.ui.views;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.objectedge.store.R;


/**
 * TODO: document your custom view class.
 */
public class ViewPageIndicator extends FrameLayout {

    private int index = 0;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private LinearLayout indicatorLayout;

    public ViewPageIndicator(Context context) {
        super(context);
        initView();
    }

    public ViewPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        adapter = pagerAdapter;
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(onPageChangeListener);
        initIndicators();
    }

    private void initView(){
        inflate(getContext(), R.layout.view_page_indicator, this);
        viewPager = (ViewPager) findViewById(R.id.pager);
        indicatorLayout = (LinearLayout) findViewById(R.id.pager_indicator);
    }

    private void initIndicators(){
        for(int i = 0; i < adapter.getCount(); i++){
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.image_view_indicator, null);
            if(i == 0){
                imageView.setImageResource(R.drawable.circle_grey);
            }
            indicatorLayout.addView(imageView);
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            ((ImageView)indicatorLayout.getChildAt(index)).setImageResource(R.drawable.circle_black);
            ((ImageView)indicatorLayout.getChildAt(position)).setImageResource(R.drawable.circle_grey);
            index = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
