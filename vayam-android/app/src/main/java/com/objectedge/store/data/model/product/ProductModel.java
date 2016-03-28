package com.objectedge.store.data.model.product;

import com.google.gson.annotations.SerializedName;
import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;

import java.util.List;
import java.util.Random;

/**
 * Created by "Michael Katkov" on 9/10/2015.
 */
public class ProductModel implements IBaseModel{

    private List<LinkModel> links;
    private List<String> x_siteName;
    private String itemID;
    private String x_thumbnailImageURL;
    private Description description;
    private String merchandiseHierarchy;
    private ItemPrice itemPrice;
    private List<String> x_siteId;

    public ProductModel(List<LinkModel> links, List<String> x_siteName, String itemID,
                        String x_thumbnailImageURL, Description description,
                        String merchandiseHierarchy, ItemPrice itemPrice, List<String> x_siteId) {
        this.links = links;
        this.x_siteName = x_siteName;
        this.itemID = itemID;
        this.x_thumbnailImageURL = x_thumbnailImageURL;
        this.description = description;
        this.merchandiseHierarchy = merchandiseHierarchy;
        this.itemPrice = itemPrice;
        this.x_siteId = x_siteId;
    }

    public ProductModel() {
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public List<String> getX_siteName() {
        return x_siteName;
    }

    public void setX_siteName(List<String> x_siteName) {
        this.x_siteName = x_siteName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getX_thumbnailImageURL() {
        return x_thumbnailImageURL;
    }

    public void setX_thumbnailImageURL(String x_thumbnailImageURL) {
        this.x_thumbnailImageURL = x_thumbnailImageURL;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getMerchandiseHierarchy() {
        return merchandiseHierarchy;
    }

    public void setMerchandiseHierarchy(String merchandiseHierarchy) {
        this.merchandiseHierarchy = merchandiseHierarchy;
    }

    public ItemPrice getItemPrice() {
        return itemPrice;
    }

    public String getItemPriceFake(){
        Random random = new Random();
        int dollars = random.nextInt(100);
        int cents = random.nextInt(100);
        return "$ " + dollars + "." + cents;
    }

    public void setItemPrice(ItemPrice itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<String> getX_siteId() {
        return x_siteId;
    }

    public void setX_siteId(List<String> x_siteId) {
        this.x_siteId = x_siteId;
    }

    public static class Description {

        @SerializedName("short") private String shortValue;
        @SerializedName("long") private String longValue;
        private String web;

        public Description(String shortValue, String longValue, String web) {
            this.shortValue = shortValue;
            this.longValue = longValue;
            this.web = web;
        }

        public Description() {
        }

        public String getShortValue() {
            return shortValue;
        }

        public void setShortValue(String shortValue) {
            this.shortValue = shortValue;
        }

        public String getLongValue() {
            return longValue;
        }

        public void setLongValue(String longValue) {
            this.longValue = longValue;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }
    }

    private static class ItemPrice {

    }
}
