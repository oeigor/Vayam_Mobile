package com.objectedge.store.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.objectedge.store.data.model.product.ProductModel;
import com.objectedge.store.ui.views.ProductItemView;

import java.util.List;

/**
 * Created by "Michael Katkov" on 10/2/2015.
 */
public class BannerFeedAdapter extends RecyclerView.Adapter<BannerFeedAdapter.ViewHolder>{

    private List<ProductModel> productModelList;
    private View.OnClickListener onClickListener;

    public BannerFeedAdapter(List<ProductModel> productModelList, View.OnClickListener onClickListener){
        this.productModelList = productModelList;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemView productItemView = new ProductItemView(parent.getContext());
        ViewHolder vh = new ViewHolder(productItemView, onClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productItemView.setData(productModelList.get(position));
        holder.productItemView.setTag(productModelList.get(position));
        holder.productItemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ProductItemView productItemView;
        public View.OnClickListener onClickListener;

        public ViewHolder(ProductItemView productItemView, View.OnClickListener onClickListener) {
            super(productItemView);
            this.productItemView = productItemView;
            this.onClickListener = onClickListener;
        }
    }
}
