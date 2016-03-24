package com.objectedge.store.data.model.fake;

import com.objectedge.store.annotations.Fake;
import com.objectedge.store.data.model.IBaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloor_000 on 3/23/2015.
 */
@Fake
public class CategoryModel implements IBaseModel {

    private String category_id;
    private String category_name;
    private String count;
    private List<CategoryModel> children;

    public CategoryModel(String category_id, String category_name, String count, List<CategoryModel> children) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.count = count;
        this.children = children;
    }

    public CategoryModel() {
        children = new ArrayList<>();
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<CategoryModel> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryModel> children) {
        this.children = children;
    }

    public void addChildren(CategoryModel categoryModel){
        children.add(categoryModel);
    }

    public List<CategoryModel> getChildrenWithCount(){
        List<CategoryModel> resultList = new ArrayList<>();
        for(CategoryModel categoryModel: children){
            if(Integer.parseInt(categoryModel.getCount()) > 0) {
                resultList.add(categoryModel);
            }
        }
        return resultList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryModel)) return false;

        CategoryModel that = (CategoryModel) o;

        if (category_id != null ? !category_id.equals(that.category_id) : that.category_id != null)
            return false;
        if (category_name != null ? !category_name.equals(that.category_name) : that.category_name != null)
            return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        return !(children != null ? !children.equals(that.children) : that.children != null);

    }

    @Override
    public int hashCode() {
        int result = category_id != null ? category_id.hashCode() : 0;
        result = 31 * result + (category_name != null ? category_name.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
