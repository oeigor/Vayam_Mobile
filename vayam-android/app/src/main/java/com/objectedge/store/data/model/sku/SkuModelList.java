package com.objectedge.store.data.model.sku;


import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by "Michael Katkov" on 10/8/2015.
 */
public class SkuModelList implements IBaseModel{

    private List<LinkModel> links;
    private List<SkuModel> items;

    public SkuModelList(){
        links = new ArrayList<>();
        items = new ArrayList<>();
    }


    public List<LinkModel> getLinks() {
        return links;
    }


    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }


    public List<SkuModel> getItems() {
        return items;
    }


    public void setItems(List<SkuModel> items) {
        this.items = items;
    }
}
