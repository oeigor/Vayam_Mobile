package com.objectedge.store.data.model.order;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "Michael Katkov" on 10/6/2015.
 */
public class AddItemToOrderModel implements IBaseModel{

    private String itemId;
    private int quantity;
    private List<MerchandiseHierarchy> merchandiseHierarchy;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<MerchandiseHierarchy> getMerchandiseHierarchy() {
        return merchandiseHierarchy;
    }

    public void setMerchandiseHierarchy(List<MerchandiseHierarchy> merchandiseHierarchy) {
        this.merchandiseHierarchy = merchandiseHierarchy;
    }


    public static AddItemToOrderModel create(String productId, String skuId) {
        AddItemToOrderModel addItemToOrderModel = new AddItemToOrderModel();
        addItemToOrderModel.setItemId(skuId);
        addItemToOrderModel.setQuantity(1);
        MerchandiseHierarchy merchandiseHierarchy = new MerchandiseHierarchy();
        merchandiseHierarchy.setValue(productId);
        merchandiseHierarchy.setTypeCode("PRODUCT");
        List<MerchandiseHierarchy> merchandiseHierarchies = new ArrayList<>();
        merchandiseHierarchies.add(merchandiseHierarchy);
        addItemToOrderModel.setMerchandiseHierarchy(merchandiseHierarchies);
        return addItemToOrderModel;
    }

    public static class MerchandiseHierarchy {
        private String value;
        private String typeCode;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }
    }

    public String buildJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
