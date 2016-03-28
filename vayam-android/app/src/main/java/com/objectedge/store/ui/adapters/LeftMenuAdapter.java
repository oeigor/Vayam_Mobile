package com.objectedge.store.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.objectedge.store.data.model.fake.CategoryModel;
import com.objectedge.store.ui.views.LeftMenuItemView;
import com.objectedge.store.utils.StringUtils;

import java.util.List;


/**
 * Created by eloor_000 on 3/26/2015.
 */
public class LeftMenuAdapter extends ArrayAdapter<CategoryModel> {

    private List<CategoryModel> modelList;

    public LeftMenuAdapter(Context context, int resource, List<CategoryModel> objects) {
        super(context, resource, objects);
        modelList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftMenuItemView view = (LeftMenuItemView) convertView;
        if (view == null) {
            view = new LeftMenuItemView(getContext());
        }
        CategoryModel categoryModel = getItem(position);
        String categoryName = categoryModel.getCategory_name().toLowerCase();
        view.setText(StringUtils.wordsToCapital(categoryName));
        view.setTag(modelList);
        return view;
    }
}
