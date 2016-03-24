package com.objectedge.store.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.views.FavoritesItemView;

import java.util.List;

/**
 * Created by eloor_000 on 5/4/2015.
 */
public class FavoritesGridAdapter extends ArrayAdapter<ProductModel> {

    public FavoritesGridAdapter(Context context, List<ProductModel> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavoritesItemView view = new FavoritesItemView(getContext());
        view.setData(getItem(position));
        return view;
    }

}
