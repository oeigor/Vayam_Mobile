package com.objectedge.store.ui.dialogs;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.manager.ProductsManager;
import com.objectedge.store.data.model.order.AddItemToOrderModel;
import com.objectedge.store.data.model.sku.SkuModel;
import com.objectedge.store.ui.adapters.SkusDialogAdapter;


/**
 * Created by "Michael Katkov" on 10/8/2015.
 */
public class SkusDialog extends DialogFragment implements ModelObserver{

    public static final String TAG = "SkusDialog";

    private static final String PRODUCT_ID = "product_id";

    private String mProductId;
    private ListView skusList;
    private ProgressBar progressBar;
    private SkusDialogAdapter skusDialogAdapter;

    private ProductsManager productsManager;
    private OrdersManager ordersManager;

    public static SkusDialog newInstance(String productId){
        SkusDialog fragment = new SkusDialog();
        Bundle args = new Bundle();
        args.putString(PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }

    public SkusDialog(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (dialog != null) {
            dialog.getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.popup_sizes_window_width),
                                         getResources().getDimensionPixelSize(R.dimen.popup_sizes_window_width));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        productsManager = ((StoreApplication) getContext().getApplicationContext()).getProductsManager();
        productsManager.clearSkus();
        ordersManager = ((StoreApplication) getContext().getApplicationContext()).getOrdersManager();
        if (getArguments() != null) {
            mProductId = getArguments().getString(PRODUCT_ID);
        }
        setStyle(DialogFragment.STYLE_NORMAL, getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_skus_layout, container, false);
        skusList = (ListView) view.findViewById(R.id.skus_list);
        progressBar = (ProgressBar) view.findViewById(R.id.skus_progress);
        skusDialogAdapter = new SkusDialogAdapter(getContext(),0,productsManager.getSkus());
        skusList.setAdapter(skusDialogAdapter);
        skusList.setOnItemClickListener(onItemClickListener);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        productsManager.addObserver(this);
        //get skus
        productsManager.getSkusByProduct(mProductId);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        productsManager.removeObserver(this);
    }


    @Override
    public void modelChanged(String message, String error) {
        if(message.equals(ProductsManager.TAG)){
            if (error == null) {
                progressBar.setVisibility(View.GONE);
                skusDialogAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Server error. Try one more time", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SkuModel currentModel = (SkuModel) parent.getItemAtPosition(position);
            AddItemToOrderModel addItemToOrderModel = AddItemToOrderModel.create(mProductId, currentModel.getX_skuId());
            ordersManager.addItemsToOrder(addItemToOrderModel);
            dismiss();
        }
    };

}
