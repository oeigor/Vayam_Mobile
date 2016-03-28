package com.objectedge.store.utils;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by eloor_000 on 5/25/2015.
 */
public class HelpUtils {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static List<String> getAllCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }
        return countries;
    }


    public static List<String> genTenYearsList() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<String> yearsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            yearsList.add(String.valueOf(year + i));
        }
        return yearsList;
    }


    public static boolean validateEmail(final String hex) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
