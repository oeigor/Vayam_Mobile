package com.objectedge.store.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.FavoritesManager;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.model.order.AddItemToOrderModel;
import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.adapters.ProductPagerAdapter;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.OkDialog;
import com.objectedge.store.ui.dialogs.SkusDialog;
import com.objectedge.store.ui.views.VerticalViewPageIndicator;
import com.objectedge.store.utils.WLog;

import java.util.Random;

public class ProductFragment extends Fragment implements ModelObserver{

    public static final String TAG = "ProductFragment";

    private static final String PRODUCT_ID = "product_id";

    private String productId;

    //Views
    private VerticalViewPageIndicator productViewPager;
    private TextView titleTV;
    private TextView priceTV;
    private FloatingActionButton infoButton;
    private FloatingActionButton addToFavorites;
    private FloatingActionButton addToCart;

    //Managers
    //private NetworkManager networkManager;
    private ProductsManager productsManager;
    private FavoritesManager favoritesManager;
    //private CurrencyManager currencyManager;
    //private AccessManager profileManager;
    private OrdersManager ordersManager;

    private OnFragmentInteractionListener listener;

    private GestureDetector.OnDoubleTapListener onDoubleTapListener = new GestureDetector.OnDoubleTapListener() {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            WLog.d(TAG, "OnDoubleTap");
            if (titleTV.getVisibility() == View.VISIBLE) {
                hideAdditionalViews();
            } else {
                showAdditionalViews();
            }
            return true;
        }


        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            WLog.d(TAG, "OnDoubleTapEvent");
            return false;
        }
    };


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param productId of current product
     * @return A new instance of fragment ProductFragment.
     */
    public static ProductFragment newInstance(String productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }


    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        if (getArguments() != null) {
            productId = getArguments().getString(PRODUCT_ID);
        }
        */
        StoreApplication application = (StoreApplication) getActivity().getApplication();
        favoritesManager = application.getFavoritesManager();
        //networkManager = application.getNetworkManager();
        productsManager = application.getProductsManager();
        //currencyManager = application.getCurrencyManager();
        //profileManager = application.getAccessManager();
        ordersManager = application.getOrdersManager();
        //productsManager.clearCurrentProduct();
        //networkManager.getProductByProductId(productId, productCallback);
    }


    @Override
    public void onStart() {
        super.onStart();
        favoritesManager.addObserver(this);
        ordersManager.addObserver(this);
    }


    @Override
    public void onStop() {
        super.onStop();
        favoritesManager.removeObserver(this);
        ordersManager.removeObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        //Pager info
        productViewPager = (VerticalViewPageIndicator) view.findViewById(R.id.product_pager);
        ProductPagerAdapter productPagerAdapter = new ProductPagerAdapter(getActivity(),
                productsManager.getCurrentProduct(), onDoubleTapListener);
        productViewPager.setAdapter(productPagerAdapter);

        //it is views, which contain information about product in top panel
        titleTV = (TextView) view.findViewById(R.id.product_title);
        priceTV = (TextView) view.findViewById(R.id.product_price);

        if (productsManager.getCurrentProduct().getDescription().getShortValue() != null) {
            titleTV.setText(productsManager.getCurrentProduct().getDescription().getShortValue());
        }
        priceTV.setText(productsManager.getCurrentProduct().getItemPriceFake());

        infoButton = (FloatingActionButton) view.findViewById(R.id.product_info_button);
        infoButton.setOnClickListener(infoImageButtonClick);

        addToFavorites = (FloatingActionButton) view.findViewById(R.id.add_to_favorites);
        addToFavorites.setOnClickListener(addToFavoritesClick);

        addToCart = (FloatingActionButton) view.findViewById(R.id.add_to_bag);
        addToCart.setOnClickListener(addToCartClick);

        //check if this product in favorites
        checkFavorites();

        return view;
    }


    private View.OnClickListener addToFavoritesClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ProductModel curProduct = productsManager.getCurrentProduct();
            if (!favoritesManager.contains(curProduct)) {
                favoritesManager.addToFavorites(curProduct);
                showOKDialog(getString(R.string.item_added_to_favorites));
            } else {
                favoritesManager.removeFromFavorites(curProduct);
                showOKDialog(getString(R.string.item_removed_from_favorites));
            }
        }
    };

    private View.OnClickListener addToCartClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ProductModel curProduct = productsManager.getCurrentProduct();
            final SkusDialog skusDialog = SkusDialog.newInstance(curProduct.getItemID());
            DialogFactory.showDialog((AppCompatActivity) getActivity(), skusDialog);
        }
    };

    private View.OnClickListener infoImageButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            showOKDialog(productsManager.getCurrentProduct().getDescription().getLongValue());
        }
    };


    private void showOKDialog(String message) {
        final OkDialog alarmDialogFragment = OkDialog.newInstance(message);
        alarmDialogFragment.setOnDialogButtonsClickListener(new OkDialog.OnDialogButtonsClickListener() {

            @Override
            public void onOK() {
                alarmDialogFragment.dismiss();
            }
        });
        DialogFactory.showDialog((AppCompatActivity) getActivity(), alarmDialogFragment);
    }


    private void checkFavorites() {
        //check if this product in favorites
        ProductModel curProduct = productsManager.getCurrentProduct();
        if (favoritesManager.contains(curProduct)) {
            addToFavorites.setImageResource(R.drawable.ic_favorite_white_36dp);
        } else {
            addToFavorites.setImageResource(R.drawable.ic_favorite_border_white_36dp);
        }
    }


    private void showAdditionalViews() {
        titleTV.setVisibility(View.VISIBLE);
        fadeIn(titleTV);
        priceTV.setVisibility(View.VISIBLE);
        fadeIn(priceTV);
        infoButton.setVisibility(View.VISIBLE);
        fadeIn(infoButton);
        addToFavorites.setVisibility(View.VISIBLE);
        fadeIn(addToFavorites);
        addToCart.setVisibility(View.VISIBLE);
        fadeIn(addToCart);
    }


    private void hideAdditionalViews() {
        titleTV.setVisibility(View.GONE);
        fadeOut(titleTV);
        priceTV.setVisibility(View.GONE);
        fadeOut(priceTV);
        infoButton.setVisibility(View.GONE);
        fadeOut(infoButton);
        addToFavorites.setVisibility(View.GONE);
        fadeOut(addToFavorites);
        addToCart.setVisibility(View.GONE);
        fadeOut(addToCart);
    }


    private void fadeIn(View view) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);
        view.setAnimation(fadeIn);
    }


    private void fadeOut(View view) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new DecelerateInterpolator()); //and this
        fadeOut.setDuration(1000);
        view.setAnimation(fadeOut);
    }


    @Override
    public void modelChanged(String message, String Error) {
        if (message.equals(OrdersManager.ADD_ITEM_TO_ORDER_SUCCESS)) {

        } else
            if (message.equals(OrdersManager.ADD_ITEM_TO_ORDER_FAILED)) {

            }
        checkFavorites();

    }
    /*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    */



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        public void onShowSizeGuide();
    }
}
