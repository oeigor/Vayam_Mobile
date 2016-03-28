package com.objectedge.store.ui.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.OrdersManager;
import com.objectedge.store.data.model.order.AddPaymentGroupModel;
import com.objectedge.store.data.model.order.AddShippingToOrderModel;
import com.objectedge.store.data.model.order.TransactionModel;
import com.objectedge.store.data.model.order.TransactionResponseModel;
import com.objectedge.store.ui.activities.BaseActivity;
import com.objectedge.store.ui.activities.MainActivity;
import com.objectedge.store.ui.adapters.OrderGridAdapter;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.OkDialog;
import com.objectedge.store.ui.dialogs.ProgressDialogFragment;
import com.objectedge.store.ui.views.AddressInfoView;
import com.objectedge.store.ui.views.CostInfoView;
import com.objectedge.store.ui.views.PaymentBlockView;
import com.objectedge.store.utils.LayoutUtils;
import com.objectedge.store.utils.LocalMessenger;
import in.srain.cube.views.GridViewWithHeaderAndFooter;


/**
 * Created by eloor_000 on 5/16/2015.
 */
public class OrderFragment extends Fragment implements ModelObserver {

    public static final String TAG = "OrderFragment";

    private GridViewWithHeaderAndFooter gridView;
    private OrderGridAdapter            orderGridAdapter;
    private CostInfoView                costInfoView;
    private FloatingActionButton        addPaymentInfoExpandButton;
    private LinearLayout                paymentInfoExpandLayout;
    private PaymentBlockView            paymentInfoView;
    private FloatingActionButton        addShippingInfoExpandButton;
    private LinearLayout                shippingInfoExpandLayout;
    private AddressInfoView             addressInfoView;
    private Button                      checkoutButton;
    private LinearLayout                noItemsLayout;
    private Button                      continueShoppingBtn;

    //private boolean needToStartCheckout;

    private OrdersManager ordersManager;

    private OnFragmentInteractionListener listener;

    private ProgressDialogFragment progress;


    public OrderFragment() {

    }


    public static OrderFragment newInstance() {
        return new OrderFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersManager = ((StoreApplication) getActivity().getApplication()).getOrdersManager();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_layout, container, false);
        gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.order_grid);
        gridView.setNumColumns(LayoutUtils.getFavoritesGridColumnCount(getActivity()));
        initFooter();
        //initHeader();
        orderGridAdapter = new OrderGridAdapter(getActivity(), ordersManager.getCurrentOrder().getLineItems());
        gridView.setAdapter(orderGridAdapter);
        noItemsLayout = (LinearLayout) view.findViewById(R.id.order_no_items_layout);
        continueShoppingBtn = (Button) view.findViewById(R.id.order_continue_shopping);
        continueShoppingBtn.setOnClickListener(continueShoppingClick);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ordersManager.addObserver(this);
        checkIfThereAreSomeItems();
    }


    @Override
    public void onPause() {
        super.onPause();
        ordersManager.removeObserver(this);
    }


    @Override
    public void modelChanged(String value, String error) {
        checkIfThereAreSomeItems();
        switch (value) {
        case OrdersManager.ADD_SHIPPING_GROUP_SUCCESS:
            AddPaymentGroupModel addPaymentModel = paymentInfoView.toAddPaymentModel();
            ordersManager.addPaymentGroupToOrder(addressInfoView.addAddressToPayment(addPaymentModel));
            break;
        case OrdersManager.ADD_SHIPPING_GROUP_FAILED:
        case OrdersManager.ADD_PAYMENT_GROUP_FAILED:
        case OrdersManager.TRANSACTION_FAILED:
            hideProgressDialog();
            break;
        case OrdersManager.ADD_PAYMENT_GROUP_SUCCESS:
            ordersManager.makeTransaction(paymentInfoView.getCCV());
            break;
        case OrdersManager.TRANSACTION_SUCCESS:
            TransactionResponseModel transactionResponseModel = ordersManager.getTransactionResponse();
            if (transactionResponseModel != null) {
                if (!transactionResponseModel.getResultCode()) {
                    showOKDialog(transactionResponseModel.getErrorMessages());
                } else {
                    showCongratsDialog();
                }

            } else {
                showOKDialog("Server error");
            }
            break;
        }
    }


    private void checkIfThereAreSomeItems() {
        if (ordersManager.getCurrentOrder().getLineItems().size() > 0) {
            //show and notify grid to update
            gridView.setVisibility(View.VISIBLE);
            orderGridAdapter.notifyDataSetChanged();
            //hide no items layout
            noItemsLayout.setVisibility(View.GONE);
            //process gift wrapper
            //giftWrapChBx.setText(getPreparedGiftWrapPrice());
            //show new prices
            costInfoView.showPrices();
        } else {
            gridView.setVisibility(View.GONE);
            noItemsLayout.setVisibility(View.VISIBLE);
        }
    }


    private void initFooter() {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_order_footer, null);
        //Total prices
        costInfoView = (CostInfoView) view.findViewById(R.id.order_cost_info);
        //Payment expander
        addPaymentInfoExpandButton = (FloatingActionButton) view.findViewById(R.id.order_add_payment_info_button);
        addPaymentInfoExpandButton.setOnClickListener(onPaymentExpand);
        paymentInfoExpandLayout = (LinearLayout) view.findViewById(R.id.order_payment_expand_layout);
        paymentInfoView = (PaymentBlockView) view.findViewById(R.id.order_payment_info);
        //Shipping expander
        addShippingInfoExpandButton = (FloatingActionButton) view.findViewById(R.id.order_add_sipping_info_button);
        addShippingInfoExpandButton.setOnClickListener(onShippingExpand);
        shippingInfoExpandLayout = (LinearLayout) view.findViewById(R.id.order_shipping_expand_layout);
        addressInfoView = (AddressInfoView) view.findViewById(R.id.order_address_info);
        //make checkout
        checkoutButton = (Button) view.findViewById(R.id.order_checkout);
        checkoutButton.setOnClickListener(onCheckoutButtonClick);
        gridView.addFooterView(view);
    }


    private View.OnClickListener onPaymentExpand = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (paymentInfoExpandLayout.getVisibility() == View.VISIBLE) {
                paymentInfoExpandLayout.setVisibility(View.GONE);
            } else {
                paymentInfoExpandLayout.setVisibility(View.VISIBLE);
            }
        }
    };

    private View.OnClickListener onShippingExpand = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (shippingInfoExpandLayout.getVisibility() == View.VISIBLE) {
                shippingInfoExpandLayout.setVisibility(View.GONE);
            } else {
                shippingInfoExpandLayout.setVisibility(View.VISIBLE);
            }
        }
    };

    private View.OnClickListener onCheckoutButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //TODO Do checkout here
            boolean checkFields = paymentInfoView.areValidFields() & addressInfoView.isValidInfo();
            if (checkFields) {
                showProgressDialog();
                AddShippingToOrderModel addShippingToOrderModel = addressInfoView.constructAddShippingAddressModel();
                ordersManager.changeShippingGroup(addShippingToOrderModel);
            } else {
                showOKDialog("Fill all fields with asterisks");
                shippingInfoExpandLayout.setVisibility(View.VISIBLE);
                paymentInfoExpandLayout.setVisibility(View.VISIBLE);
            }
        }
    };


    private void showOKDialog(String message) {
        final OkDialog alarmDialogFragment = OkDialog.newInstance(message);
        alarmDialogFragment.setOnDialogButtonsClickListener(new OkDialog.OnDialogButtonsClickListener() {

            @Override
            public void onOK() {
                hideProgressDialog();
                alarmDialogFragment.dismiss();
            }
        });
        DialogFactory.showDialog((AppCompatActivity) getActivity(), alarmDialogFragment);
    }


    private void showCongratsDialog() {
        final OkDialog congratsFragment = OkDialog.newInstance("Congratulation! Your order is complete");
        congratsFragment.setOnDialogButtonsClickListener(new OkDialog.OnDialogButtonsClickListener() {

            @Override
            public void onOK() {
                hideProgressDialog();
                congratsFragment.dismiss();
                MainActivity.startFromTheBegin(getContext(), true);
            }
        });
        DialogFactory.showDialog((AppCompatActivity) getActivity(), congratsFragment);
    }


    private void showProgressDialog() {
        progress = ProgressDialogFragment.newInstance();
        DialogFactory.showDialog((AppCompatActivity) getActivity(), progress);
    }


    private void hideProgressDialog() {
        if (progress != null) {
            progress.dismiss();
            if (progress.getDialog() != null) {
                progress.getDialog().dismiss();
            }
        }
    }


    private View.OnClickListener continueShoppingClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            listener.onContinueShopping();
        }
    };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onContinueShopping();
    }
}
