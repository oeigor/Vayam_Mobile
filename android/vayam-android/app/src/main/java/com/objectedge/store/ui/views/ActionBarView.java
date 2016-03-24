package com.objectedge.store.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.FavoritesManager;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.OrdersManager;


/**
 * Created by eloor_000 on 3/25/2015.
 */
public class ActionBarView extends RelativeLayout implements ModelObserver {

    private ImageView menuButton;
    private ImageView searchButton;
    private ImageView logoButton;
    private RelativeLayout favoritesRL;
    private TextView favoritesCounter;
    private ImageView favoritesStar;
    private RelativeLayout bagRL;
    private TextView bagCounter;
    private ImageView bagIcon;
    private ClickListener clickListener;

    public ActionBarView(Context context) {
        super(context);
        initView();
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.action_bar_layout, this);
        menuButton = (ImageView) findViewById(R.id.menu);
        searchButton = (ImageView) findViewById(R.id.search);
        logoButton = (ImageView) findViewById(R.id.logo);
        favoritesRL = (RelativeLayout) findViewById(R.id.favorites);
        favoritesCounter = (TextView) findViewById(R.id.favoritesCounter);
        favoritesStar = (ImageView) findViewById(R.id.favoritesStar);
        setFavoritesCounter();
        bagRL = (RelativeLayout) findViewById(R.id.bag);
        bagCounter = (TextView) findViewById(R.id.bagCounter);
        bagIcon = (ImageView) findViewById(R.id.bagIcon);
        setBagCounter();
        initializeListeners();
    }


    private void setFavoritesCounter(){
        FavoritesManager favoritesManager = ((StoreApplication)getContext().getApplicationContext()).getFavoritesManager();
        if(favoritesManager.getFavorites().size() > 0){
            favoritesStar.setImageResource(R.drawable.ic_favorite_white_36dp);
            favoritesCounter.setVisibility(VISIBLE);
            favoritesCounter.setText(String.valueOf(favoritesManager.getFavorites().size()));
        } else {
            favoritesStar.setImageResource(R.drawable.ic_favorite_border_white_36dp);
            favoritesCounter.setVisibility(GONE);
        }
    }

    private void setBagCounter(){
        OrdersManager ordersManager = ((StoreApplication)getContext().getApplicationContext()).getOrdersManager();
        if(!ordersManager.getCurrentOrder().getLineItems().isEmpty()){
            bagCounter.setVisibility(VISIBLE);
            bagCounter.setText(String.valueOf(ordersManager.getCurrentOrder().getLineItems().size()));
        } else {
            bagCounter.setVisibility(GONE);
        }
    }

    private void initializeListeners(){
        menuButton.setOnClickListener(onClickListener);
        searchButton.setOnClickListener(onClickListener);
        logoButton.setOnClickListener(onClickListener);
        favoritesRL.setOnClickListener(onClickListener);
        favoritesCounter.setOnClickListener(onClickListener);
        favoritesStar.setOnClickListener(onClickListener);
        bagRL.setOnClickListener(onClickListener);
        bagCounter.setOnClickListener(onClickListener);
        bagIcon.setOnClickListener(onClickListener);
    }

    @Override
    public void modelChanged(String message, String error) {
        setFavoritesCounter();
        setBagCounter();
    }

    public interface ClickListener{
        void onMenu();
        void onSearch();
        void onLogo();
        void onFavorites();
        void onBag();
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if(clickListener != null) {
                switch (v.getId()) {
                    case R.id.menu:
                        clickListener.onMenu();
                        break;
                    case R.id.search:
                        clickListener.onSearch();
                        break;
                    case R.id.logo:
                        clickListener.onLogo();
                        break;
                    case R.id.favorites:
                    case R.id.favoritesCounter:
                    case R.id.favoritesStar:
                        clickListener.onFavorites();
                        break;
                    case R.id.bag:
                    case R.id.bagCounter:
                    case R.id.bagIcon:
                        clickListener.onBag();
                        break;
                }
            }
        }

    };
}
