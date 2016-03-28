package com.objectedge.store.data.model.product;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;
import com.objectedge.store.data.model.PagingModel;

import java.util.List;

/**
 * Created by "Michael Katkov" on 9/11/2015.
 */
public class ProductsListModel implements IBaseModel{

    private List<LinkModel> links;
    private List<ProductModel> items;
    private PagingModel paging;

    public ProductsListModel(PagingModel paging, List<ProductModel> items, List<LinkModel> links) {
        this.paging = paging;
        this.items = items;
        this.links = links;
    }

    public ProductsListModel() {
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public List<ProductModel> getItems() {
        return items;
    }

    public void setItems(List<ProductModel> items) {
        this.items = items;
    }

    public PagingModel getPaging() {
        return paging;
    }

    public void setPaging(PagingModel paging) {
        this.paging = paging;
    }
}
