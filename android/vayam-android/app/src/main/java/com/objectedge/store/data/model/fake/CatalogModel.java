package com.objectedge.store.data.model.fake;


import com.objectedge.store.annotations.Fake;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.PagingModel;

import java.util.List;

/**
 * Created by eloor_000 on 3/25/2015.
 */
@Fake
public class CatalogModel implements IBaseModel {

    private List<CategoryModel> categoryModelList;

    public CatalogModel(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public CatalogModel() {
    }

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }
}
