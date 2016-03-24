package com.objectedge.store.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.objectedge.store.R;
import com.objectedge.store.utils.ColorUtil;
import com.objectedge.store.utils.ImageUtils;

import java.util.List;


/**
 * Created by eloor_000 on 5/5/2015.
 */
public class SimpleSpinnerAdapter extends ArrayAdapter<String> {

    private LayoutInflater layoutInflater;
    private boolean isEnabled     = true;
    private boolean isErrorMarked = false;


    public SimpleSpinnerAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }


    public SimpleSpinnerAdapter(Context context, String[] objects) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_spinner_layout, parent, false);
        }
        TextView textV = (TextView) view.findViewById(R.id.spinnerItem);
        textV.setText(getItem(position));
        if (isErrorMarked) {
            textV.setBackgroundColor(ColorUtil.getColor(getContext(), R.color.pink));
        } else {
            textV.setEnabled(isEnabled);
        }
        return view;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_spinner_dropdown_layout, parent, false);
        }
        TextView textV = (TextView) view.findViewById(R.id.spinnerDropDownItem);
        textV.setText(getItem(position));
        return view;
    }


    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        notifyDataSetChanged();
    }


    public void setErrorMarked(boolean isErrorMarked) {
        this.isErrorMarked = isErrorMarked;
        notifyDataSetChanged();
    }
}
