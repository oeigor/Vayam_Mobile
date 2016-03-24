package com.objectedge.store.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.OrdersManager;


/**
 * Created by eloor_000 on 5/27/2015.
 */
public class CostInfoView extends LinearLayout {

    private LinearLayout subTotalLayout;
    private TextView     subTotalTv;
    private LinearLayout discountLayout;
    private TextView     discountTv;
    private LinearLayout shippingLayout;
    private TextView     shippingTv;
    private LinearLayout totalLayout;
    private TextView     totalTv;

    private OrdersManager ordersManager;


    public CostInfoView(Context context) {
        super(context);
        initView();
        initManagers();
    }


    public CostInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initManagers();
    }


    private void initManagers() {
        ordersManager = ((StoreApplication) getContext().getApplicationContext()).getOrdersManager();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_cost_info_layout, this);
        subTotalLayout = (LinearLayout) findViewById(R.id.cost_info_sub_total_layout);
        subTotalTv = (TextView) findViewById(R.id.cost_info_sub_total_tv);
        discountLayout = (LinearLayout) findViewById(R.id.cost_info_discount_layout);
        discountTv = (TextView) findViewById(R.id.cost_info_discount_tv);
        shippingLayout = (LinearLayout) findViewById(R.id.cost_info_shipping_layout);
        shippingTv = (TextView) findViewById(R.id.cost_info_shipping_tv);
        totalLayout = (LinearLayout) findViewById(R.id.cost_info_total_layout);
        totalTv = (TextView) findViewById(R.id.cost_info_total_tv);
        //showPrices();
    }


    public void showPrices() {
        setSubTotal();
        setDiscount();
        setShipping();
        setTotal();
    }


    private void setSubTotal() {
        String currency = ordersManager.getCurrentOrder().getTotals().getCurrencyCode();
        String subTotal = ordersManager.getCurrentOrder().getTotals().getTransactionNetAmount();
        if(subTotal != null) {
            if (Double.parseDouble(subTotal) > 0) {
                subTotalLayout.setVisibility(View.VISIBLE);
                String toShow = subTotal + " " + currency;
                subTotalTv.setText(toShow);
            } else {
                subTotalLayout.setVisibility(View.GONE);
            }
        }
    }


    private void setDiscount() {
        //        double discount = ((StoreApplication)getContext().getApplicationContext()).getOrdersManager().getCurrentOrder().getDiscount();
        //        if(discount > 0){
        //            discountLayout.setVisibility(View.VISIBLE);
        //            discountTv.setText(((StoreApplication) getContext().getApplicationContext()).getCurrencyManager().formatPrice(
        //                    String.valueOf(discount)));
        //        } else {
        discountLayout.setVisibility(View.GONE);
        //        }
    }


    private void setShipping() {
        //        double shipping = ((StoreApplication)getContext().getApplicationContext()).getOrdersManager().getCurrentOrder().getShipping_cost();
        //        if(shipping > 0){
        //            shippingLayout.setVisibility(View.VISIBLE);
        //            shippingTv.setText(((StoreApplication) getContext().getApplicationContext()).getCurrencyManager().formatPrice(
        //                    String.valueOf(shipping)));
        //        } else {
        shippingLayout.setVisibility(View.GONE);
        //        }
    }


    private void setTotal() {
        String currency = ordersManager.getCurrentOrder().getTotals().getCurrencyCode();
        String total = ordersManager.getCurrentOrder().getTotals().getTransactionGrandAmount();
        if (total != null) {
            if (Double.parseDouble(total) > 0) {
                totalLayout.setVisibility(View.VISIBLE);
                String toShow = total + " " + currency;
                totalTv.setText(toShow);
            } else {
                totalLayout.setVisibility(View.GONE);
            }
        }
    }

}
