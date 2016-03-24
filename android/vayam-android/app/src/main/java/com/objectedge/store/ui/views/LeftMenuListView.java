package com.objectedge.store.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.objectedge.store.R;
import com.objectedge.store.utils.StringUtils;

import java.util.List;

/**
 * Created by eloor_000 on 3/27/2015.
 */
public class LeftMenuListView extends LinearLayout {

    private ListView listView;
    private LinearLayout headersContainer;
    private int depth;
    private int visibleHeadersCount = 0;
    private OnClickListener onHeaderListener;

    public LeftMenuListView(Context context) {
        super(context);
        initView();
    }

    public LeftMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.left_menu_list_layout, this);
        listView = (ListView) findViewById(R.id.left_menu_list);
        headersContainer = (LinearLayout) findViewById(R.id.left_menu_header_container);
    }

   /**
     * Set how many headers can be shown
     * @param depth count of headers can be shown
     */
    public void setDepth(int depth){
        this.depth = depth;
        if(depth <= 0){
            throw new IllegalArgumentException("Depth cannot be negative");
        }
        for(int i = 0; i < depth; i++){
            LeftMenuHeaderView headerView = new LeftMenuHeaderView(getContext());
            headerView.setHeaderMode();
            if(onHeaderListener != null) {
                headerView.setOnClickListener(onHeaderListener);
            }
            if(i == 0){
                headerView.setText(getContext().getString(R.string.menu));
            }
            headersContainer.addView(headerView);
        }
        for(int i = 0; i < headersContainer.getChildCount(); i++){
            headersContainer.getChildAt(i).setVisibility(GONE);
        }
    }

    /**
     * Show headers and populate listView by new data
     * @param categoryName the title, which will be set to the highlight header
     * @param current current data from the listView
     * @param adapter a data to populate the listView
     */
    public boolean showNextHeader(String categoryName, List current, ListAdapter adapter){
        boolean isHeaderAdded = false;
        //check if it is possible to show one more header
        if(depth > visibleHeadersCount) {
            //play with headers
            if( visibleHeadersCount == 0) {
                LeftMenuHeaderView menuView = (LeftMenuHeaderView) headersContainer.getChildAt(visibleHeadersCount);
                menuView.setTag(current);
                menuView.setHeaderMode();
                menuView.setVisibility(VISIBLE);
                visibleHeadersCount++;

                LeftMenuHeaderView headerView = (LeftMenuHeaderView) headersContainer.getChildAt(visibleHeadersCount);
                headerView.setText(StringUtils.wordsToCapital(categoryName));
                headerView.setHighlightMode();
                headerView.setVisibility(VISIBLE);
                visibleHeadersCount++;
            } else {
                LeftMenuHeaderView prevHeaderView = (LeftMenuHeaderView) headersContainer.getChildAt(visibleHeadersCount - 1);
                prevHeaderView.setTag(current);
                prevHeaderView.setHeaderMode();

                LeftMenuHeaderView curHeaderView = (LeftMenuHeaderView) headersContainer.getChildAt(visibleHeadersCount);
                curHeaderView.setText(StringUtils.wordsToCapital(categoryName));
                curHeaderView.setHighlightMode();
                curHeaderView.setVisibility(VISIBLE);
                visibleHeadersCount++;
            }
            //between some headers we should show dividers, but between others not.
            adjustHeadersDividers();
            //populate new data into the list with slide down animation
            slideDownNewData(adapter);
            isHeaderAdded = true;
        } else {
            isHeaderAdded = false;
        }
        return isHeaderAdded;
    }

    private void adjustHeadersDividers(){
        if(visibleHeadersCount > 2){
            for(int i = 0; i < headersContainer.getChildCount() - 2; i++){
                ((LeftMenuHeaderView)headersContainer.getChildAt(i)).setDividerVisibility(View.VISIBLE);
            }
        } else {
            for(int i = 0; i < headersContainer.getChildCount(); i++){
                ((LeftMenuHeaderView)headersContainer.getChildAt(i)).setDividerVisibility(View.GONE);
            }
        }
    }

    /**
     * Get index of position of the header
     * @param leftMenuHeaderView header
     * @return index of position
     */
    public int getPosition(LeftMenuHeaderView leftMenuHeaderView){
        int position = -1;
        for(int i = 0; i < headersContainer.getChildCount(); i++){
            if(headersContainer.getChildAt(i) == leftMenuHeaderView){
                position = i;
                break;
            }
        }
        return position;
    }

    private void slideUpNewData(ListAdapter adapter){
        //set data to listView
        listView.setAdapter(adapter);
        //animate list
        Animation animationStart = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_up);
        listView.startAnimation(animationStart);
    }

    private void slideDownNewData(ListAdapter adapter){
        //set data to listView
        listView.setAdapter(adapter);
        //animate list
        Animation animationStart = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_down);
        listView.startAnimation(animationStart);
    }

    /**
     * hide headers and populate new data to the listView
     * @param position position of header that was clicked
     * @param adapter a data to populate the listView
     */
    public void hideHeaders(int position, ListAdapter adapter) {
        //populate new data into the list with slide up animation
        slideUpNewData(adapter);
        //play with headers
        if(position == 0){
            for(int i = headersContainer.getChildCount() - 1; i >=0; i--){
                headersContainer.getChildAt(i).setTag(null);
                headersContainer.getChildAt(i).setVisibility(GONE);
            }
            visibleHeadersCount = 0;
        } else {
            for(int i = headersContainer.getChildCount() - 1; i >= position; i--){
                headersContainer.getChildAt(i).setTag(null);
                if(i == position) {
                    ((LeftMenuHeaderView)headersContainer.getChildAt(i)).setHighlightMode();
                    break;
                }
                headersContainer.getChildAt(i).setVisibility(GONE);
            }
            visibleHeadersCount = position + 1;
        }
    }

    public void setAdapter(ListAdapter adapter) {
        listView.setAdapter(adapter);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        listView.setOnItemClickListener(onItemClickListener);
    }

    public void setOnHeaderClickListener(OnClickListener onHeaderListener) {
        this.onHeaderListener = onHeaderListener;
        for(int i = 0; i < headersContainer.getChildCount(); i++){
            headersContainer.getChildAt(i).setOnClickListener(onHeaderListener);
        }
    }
}
