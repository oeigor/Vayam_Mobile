package com.objectedge.store.data.manager;


import android.content.Context;
import android.widget.Toast;

import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.model.order.AddItemToOrderModel;
import com.objectedge.store.data.model.order.AddPaymentGroupModel;
import com.objectedge.store.data.model.order.AddShippingToOrderModel;
import com.objectedge.store.data.model.order.CreateOrderModel;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.data.model.order.TransactionModel;
import com.objectedge.store.data.model.order.TransactionResponseModel;
import com.objectedge.store.network.Callback;
import com.objectedge.store.network.ResponseModel;
import com.objectedge.store.ui.activities.BaseActivity;
import com.objectedge.store.utils.LocalMessenger;
import com.objectedge.store.utils.WLog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eloor_000 on 5/14/2015.
 */
public class OrdersManager implements ModelObservable {

    public static final String TAG = "OrdersManager";

    public static final String CREATE_ORDER_SUCCESS       = "create_order_success";
    public static final String CREATE_ORDER_FAILED        = "create_order_failed";
    public static final String ADD_ITEM_TO_ORDER_SUCCESS  = "add_item_to_order_success";
    public static final String ADD_ITEM_TO_ORDER_FAILED   = "add_item_to_order_failed";
    public static final String ADD_PAYMENT_GROUP_SUCCESS  = "add_payment_group_success";
    public static final String ADD_PAYMENT_GROUP_FAILED   = "add_payment_group_failed";
    public static final String ADD_SHIPPING_GROUP_SUCCESS = "add_shipping_group_success";
    public static final String ADD_SHIPPING_GROUP_FAILED  = "add_shipping_group_failed";
    public static final String TRANSACTION_SUCCESS        = "transaction_success";
    public static final String TRANSACTION_FAILED         = "transaction_failed";
    public static final String ORDER_UPDATE_SUCCESS       = "order_update_success";
    public static final String ORDER_UPDATE_FAILED        = "order_update_failed";

    private OrderModel               currentOrder;
    private Context                  context;
    private List<ModelObserver>      observerList;
    private NetworkManager           networkManager;
    private TransactionResponseModel transactionResponse;


    public OrdersManager(Context context) {
        this.context = context;
        currentOrder = new OrderModel();
        observerList = new ArrayList<>();
        networkManager = ((StoreApplication) context.getApplicationContext()).getNetworkManager();
    }


    public OrderModel getCurrentOrder() {
        return currentOrder;
    }


    public void updateCurrentOrder(OrderModel order) {
        currentOrder.updateOrder(order);
    }


    public void updateLineItems(OrderModel.LineItem item) {
        currentOrder.addLineItem(item);
    }


    public void updatePaymentGroup(OrderModel.PaymentGroupModel paymentGroupModel) {
        currentOrder.addPayment(paymentGroupModel);
    }


    public void addShippingGroup(OrderModel.ShippingGroupModel shippingGroupModel) {
        currentOrder.addShipping(shippingGroupModel);
    }


    public void clearData() {
        currentOrder = new OrderModel();
        observerList = new ArrayList<>();
    }


    public TransactionResponseModel getTransactionResponse() {
        return transactionResponse;
    }


    public void getOrder() {
        networkManager.getOrder(currentOrder.getTransactionId(), new Callback<ResponseModel<OrderModel>>() {

            @Override
            public void onLoadStarted() {

            }


            @Override
            public void onLoadFinished(ResponseModel<OrderModel> response) {
                if (response.exception != null) {
                    Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                    notifyObservers(ORDER_UPDATE_FAILED, response.exception.getMessage());
                } else {
                    updateCurrentOrder(response.model);
                    notifyObservers(ORDER_UPDATE_SUCCESS, null);
                }
            }
        });
    }


    public void createOrder(CreateOrderModel createOrderModel) {
        networkManager.createOrder(createOrderModel, new Callback<ResponseModel<OrderModel>>() {

            @Override
            public void onLoadStarted() {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
            }


            @Override
            public void onLoadFinished(ResponseModel<OrderModel> response) {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                if (response.exception != null) {
                    Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                    notifyObservers(CREATE_ORDER_FAILED, response.exception.getMessage());
                } else {
                    updateCurrentOrder(response.model);
                    notifyObservers(CREATE_ORDER_SUCCESS, null);
                }
            }
        });
    }


    public void addItemsToOrder(AddItemToOrderModel addItemToOrderModel) {
        networkManager.addItemToOrder(currentOrder.getTransactionId(), addItemToOrderModel,
                new Callback<ResponseModel<OrderModel.LineItem>>() {

                    @Override
                    public void onLoadStarted() {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
                    }


                    @Override
                    public void onLoadFinished(ResponseModel<OrderModel.LineItem> response) {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                        if (response.exception != null) {
                            Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                            notifyObservers(ADD_ITEM_TO_ORDER_FAILED, response.exception.getMessage());
                        } else {
                            updateLineItems(response.model);
                            getOrder();
                            notifyObservers(ADD_ITEM_TO_ORDER_SUCCESS, null);
                        }
                    }
                });
    }


    public void addShippingGroup(AddShippingToOrderModel addShippingToOrderModel) {
        networkManager.addShippingToOrder(currentOrder.getTransactionId(), addShippingToOrderModel,
                new Callback<ResponseModel<OrderModel.ShippingGroupModel>>() {

                    @Override
                    public void onLoadStarted() {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
                    }


                    @Override
                    public void onLoadFinished(ResponseModel<OrderModel.ShippingGroupModel> response) {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                        if (response.exception != null) {
                            Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                            notifyObservers(ADD_SHIPPING_GROUP_FAILED, response.exception.getMessage());
                        } else {
                            addShippingGroup(response.model);
                            getOrder();
                            notifyObservers(ADD_SHIPPING_GROUP_SUCCESS, null);
                        }
                    }
                });

    }


    private String getShippingCurrentId() {
        OrderModel.ShippingGroupModel shippingGroupModel = currentOrder.getShippingGroups().get(0);
        return shippingGroupModel.getX_shippingGroupId();
    }


    public void changeShippingGroup(AddShippingToOrderModel addShippingToOrderModel) {
        networkManager
                .changeShippingInOrder(currentOrder.getTransactionId(), getShippingCurrentId(), addShippingToOrderModel,
                        new Callback<ResponseModel<OrderModel.ShippingGroupModel>>() {

                            @Override
                            public void onLoadStarted() {

                            }


                            @Override
                            public void onLoadFinished(ResponseModel<OrderModel.ShippingGroupModel> response) {
                                if (response.exception != null) {
                                    Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                                    notifyObservers(ADD_SHIPPING_GROUP_FAILED, response.exception.getMessage());
                                } else {
                                    addShippingGroup(response.model);
                                    notifyObservers(ADD_SHIPPING_GROUP_SUCCESS, null);
                                }
                            }
                        });
    }


    public void addPaymentGroupToOrder(AddPaymentGroupModel addPaymentGroupModel) {
        networkManager.addPaymentGroupToOrder(currentOrder.getTransactionId(), addPaymentGroupModel,
                new Callback<ResponseModel<OrderModel.PaymentGroupModel>>() {

                    @Override
                    public void onLoadStarted() {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
                    }


                    @Override
                    public void onLoadFinished(ResponseModel<OrderModel.PaymentGroupModel> response) {
                        //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                        if (response.exception != null) {
                            Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                            notifyObservers(ADD_PAYMENT_GROUP_FAILED, response.exception.getMessage());
                        } else {
                            updatePaymentGroup(response.model);
                            notifyObservers(ADD_PAYMENT_GROUP_SUCCESS, null);
                        }
                    }
                });
    }


    public void makeTransaction(String CCV) {
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setTransactionAction("submit");
        TransactionModel.TransactionArguments transactionArguments = new TransactionModel.TransactionArguments();
        transactionArguments.setOrderId(currentOrder.getTransactionId());
        transactionArguments.setCCV(CCV);
        transactionModel.setTransactionArguments(transactionArguments);
        networkManager.makeTransaction(transactionModel, new Callback<ResponseModel<TransactionResponseModel>>() {

            @Override
            public void onLoadStarted() {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, true);
            }


            @Override
            public void onLoadFinished(ResponseModel<TransactionResponseModel> response) {
                //LocalMessenger.sendMessage(context, BaseActivity.TAG, BaseActivity.SHOW_PROGRESS, false);
                if (response.exception != null) {
                    Toast.makeText(context, response.exception.getMessage(), Toast.LENGTH_SHORT).show();
                    notifyObservers(TRANSACTION_FAILED, response.exception.getMessage());
                    //DEBUG
                    getOrder();
                } else {
                    transactionResponse = response.model;
                    notifyObservers(TRANSACTION_SUCCESS, null);
                }
            }
        });
    }


    @Override
    public void addObserver(ModelObserver observer) {
        observerList.add(observer);
    }


    @Override
    public void removeObserver(ModelObserver observer) {
        observerList.remove(observer);
    }


    @Override
    public void notifyObservers(String message, String error) {
        //notify observers
        for (ModelObserver observer : observerList) {
            observer.modelChanged(message, error);
        }
    }

}
