package com.objectedge.store.utils;

/**
 * Created by eloor_000 on 3/26/2015.
 */
public class Urls {

    //domains
    private final static String SENDBOX_URL = "http://commerceapi.objectedge.com:7003/api/atg/v1/";
    public final static String DOMAIL_URL = SENDBOX_URL;

    //postfixes
    private final static String PROFILE_POSTFIX = "profiles";
    private final static String CATALOG_POSTFIX = "catalog/";
    private final static String BRANDS_POSTFIX = "brands/";
    private final static String PRODUCTS_POSTFIX = "products/";
    private final static String SEARCH_POSTFIX = "search/";
    private final static String WISH_LISTS_POSTFIX = "wishlists/";
    private final static String ORDERS_POSTFIX = "orders/";
    private final static String INVENTORY_POSTFIX = "inventory/";
    private final static String USERS_POSTFIX = "users/";
    private final static String TRANSACTION_POSTFIX = "transactions/";

    //urls
    public final static String PROFILE_URI = DOMAIL_URL + PROFILE_POSTFIX;
    public final static String CATALOG_URI = DOMAIL_URL + CATALOG_POSTFIX;
    public final static String BRANDS_URI = DOMAIL_URL + BRANDS_POSTFIX;
    public final static String PRODUCTS_URI = DOMAIL_URL + PRODUCTS_POSTFIX;
    public final static String SEARCH_URI = DOMAIL_URL + SEARCH_POSTFIX;
    public final static String WISH_LISTS_URI = DOMAIL_URL + WISH_LISTS_POSTFIX;
    public final static String ORDERS_URI = DOMAIL_URL + ORDERS_POSTFIX;
    public final static String INVENTORY_URI = DOMAIL_URL + INVENTORY_POSTFIX;
    public final static String USERS_URI = DOMAIL_URL + USERS_POSTFIX;
    public final static String TRANSACTION_URI = DOMAIL_URL + TRANSACTION_POSTFIX;

}
