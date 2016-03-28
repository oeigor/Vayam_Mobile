package com.objectedge.store.utils;

import java.util.regex.Pattern;

/**
 * Created by "Michael Katkov" on 10/5/2015.
 */
public class RegisterValidator {

    public static final String OK = "ok";
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("[A-Za-z0-9 ]*");

    public static boolean validateEmail(String emailText){
        return EMAIL_ADDRESS_PATTERN.matcher(emailText).matches();
    }

    public static boolean validatePassword(String password){
        return PASSWORD_PATTERN.matcher(password).matches();
    }

}
