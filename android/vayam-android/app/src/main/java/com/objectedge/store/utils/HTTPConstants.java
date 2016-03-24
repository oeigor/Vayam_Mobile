package com.objectedge.store.utils;

/**
 * Created by eloor_000 on 3/23/2015.
 */
public class HTTPConstants {

    //methods
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    //properties
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_LANGUAGE = "Content-Language";
    public static final String ACCEPT = "Accept";

    //authorization
    public static final String BASIC = "basic";
    public static final String BEARER = "bearer";
    public static final String TOKEN = "VERY_SECRET_TOKEN";
    public final static String CLIENT_KEY = "cool_app_id_1";
    public final static String SECRET_COMBINATION = "d9dabeae-ce58-4cc1-94b9-17cbcc666666";
    public static final String BASIC_TOKEN = BASIC + " " + TOKEN;
    public static final String BEARER_TOKEN = BEARER + " " + TOKEN;

    //format
    public static final String UTF_8 = "UTF-8";
    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

}
