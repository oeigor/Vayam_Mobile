package com.objectedge.store.data.model.fake;

import com.objectedge.store.data.model.IBaseModel;

import java.util.List;

/**
 * Created by eloor_000 on 5/1/2015.
 */
public class WishlistItemModel implements IBaseModel {

    private String wishlist_item_id;
    private String product_id;
    private Attributes attributes;

    public WishlistItemModel(String wishlist_item_id, String product_id, Attributes attributes) {
        this.wishlist_item_id = wishlist_item_id;
        this.product_id = product_id;
        this.attributes = attributes;
    }

    public WishlistItemModel() {
        attributes = new Attributes();
    }

    public String getWishlist_item_id() {
        return wishlist_item_id;
    }

    public void setWishlist_item_id(String wishlist_item_id) {
        this.wishlist_item_id = wishlist_item_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WishlistItemModel)) return false;

        WishlistItemModel that = (WishlistItemModel) o;

        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null)
            return false;
        if (product_id != null ? !product_id.equals(that.product_id) : that.product_id != null)
            return false;
        if (wishlist_item_id != null ? !wishlist_item_id.equals(that.wishlist_item_id) : that.wishlist_item_id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wishlist_item_id != null ? wishlist_item_id.hashCode() : 0;
        result = 31 * result + (product_id != null ? product_id.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    public static class Attributes{

        private List<Size> sizes;

        public Attributes(List<Size> sizes) {
            this.sizes = sizes;
        }

        public Attributes() {
        }

        public List<Size> getSizes() {
            return sizes;
        }

        public void setSizes(List<Size> sizes) {
            this.sizes = sizes;
        }

        public static class Size{

            private String sku_id;
            private int quantity;

            public Size(String sku_id, int quantity) {
                this.sku_id = sku_id;
                this.quantity = quantity;
            }

            public Size() {
            }

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Attributes)) return false;

            Attributes that = (Attributes) o;

            if (sizes != null ? !sizes.equals(that.sizes) : that.sizes != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return sizes != null ? sizes.hashCode() : 0;
        }
    }
}
