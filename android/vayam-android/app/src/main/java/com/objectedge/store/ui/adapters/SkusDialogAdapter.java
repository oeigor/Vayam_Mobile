package com.objectedge.store.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.objectedge.store.R;
import com.objectedge.store.data.model.sku.SkuModel;

import java.util.List;


/**
 * Created by "Michael Katkov" on 10/8/2015.
 */
public class SkusDialogAdapter extends ArrayAdapter<SkuModel> {

    private List<SkuModel> modelList;
    private LayoutInflater inflater;


    public SkusDialogAdapter(Context context, int resource, List<SkuModel> objects) {
        super(context, resource, objects);
        modelList = objects;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_sku_layout, parent, false);
            holder.sizeTv = (TextView) convertView.findViewById(R.id.item_size);
            holder.colorTv = (TextView) convertView.findViewById(R.id.item_color);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SkuModel skuModel = getItem(position);
        holder.colorTv.setText(skuModel.getX_skuProperties().getColor());
        holder.sizeTv.setText(skuModel.getX_skuProperties().getSize());
        return convertView;
    }

    public static class ViewHolder {
        TextView  sizeTv;
        TextView  colorTv;
    }
}
