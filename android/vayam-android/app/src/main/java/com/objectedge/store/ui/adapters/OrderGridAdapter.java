package com.objectedge.store.ui.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.ui.views.BagItemView;

import java.util.List;


/**
 * Created by eloor_000 on 5/16/2015.
 */
public class OrderGridAdapter extends ArrayAdapter<OrderModel.LineItem> {

    public OrderGridAdapter(Context context, List<OrderModel.LineItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BagItemView bagItemView = new BagItemView(parent.getContext());
        bagItemView.setData(getItem(position));
        return bagItemView;
    }
}
