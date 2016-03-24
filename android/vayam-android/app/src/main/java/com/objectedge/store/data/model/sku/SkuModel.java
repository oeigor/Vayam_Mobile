package com.objectedge.store.data.model.sku;


import com.google.gson.annotations.SerializedName;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.order.OrderModel;
import com.objectedge.store.data.model.product.ProductModel;

import java.util.List;


/**
 * Created by "Michael Katkov" on 10/8/2015.
 */
public class SkuModel implements IBaseModel{

    private List<OrderModel.LineItem> links;
    private Description description;
    private X_skuProperties x_skuProperties;
    private String x_skuId;
    private double itemPrice;
    private int stockLevel;


    public List<OrderModel.LineItem> getLinks() {
        return links;
    }


    public void setLinks(List<OrderModel.LineItem> links) {
        this.links = links;
    }


    public Description getDescription() {
        return description;
    }


    public void setDescription(Description description) {
        this.description = description;
    }


    public X_skuProperties getX_skuProperties() {
        return x_skuProperties;
    }


    public void setX_skuProperties(X_skuProperties x_skuProperties) {
        this.x_skuProperties = x_skuProperties;
    }


    public String getX_skuId() {
        return x_skuId;
    }


    public void setX_skuId(String x_skuId) {
        this.x_skuId = x_skuId;
    }


    public double getItemPrice() {
        return itemPrice;
    }


    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public int getStockLevel() {
        return stockLevel;
    }


    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }


    public static class Description {

        @SerializedName("short")
        private String shortValue;

        public String getShortValue() {
            return shortValue;
        }

        public void setShortValue(String shortValue) {
            this.shortValue = shortValue;
        }
    }

    public static class X_skuProperties {

        private String color;
        private String size;


        public String getColor() {
            return color;
        }


        public void setColor(String color) {
            this.color = color;
        }


        public String getSize() {
            return size;
        }


        public void setSize(String size) {
            this.size = size;
        }
    }
}
