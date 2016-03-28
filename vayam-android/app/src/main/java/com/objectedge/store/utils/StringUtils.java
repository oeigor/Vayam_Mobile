package com.objectedge.store.utils;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloor_000 on 3/28/2015.
 */
public class StringUtils {

    public static String fromCapital(String string){
        char[] chars = string.toLowerCase().toCharArray();
        char f = Character.toUpperCase(chars[0]);
        chars[0] = f;
        return String.copyValueOf(chars);
    }

    public static String wordsToCapital(String string){
        char[] chars = string.toLowerCase().toCharArray();
        char f = Character.toUpperCase(chars[0]);
        chars[0] = f;
        for(int i = 0; i < chars.length; i++){
            if(i < chars.length - 1 && chars[i] == ' '){
                f = Character.toUpperCase(chars[i + 1]);
                chars[i + 1] = f;
            }
        }
        return String.copyValueOf(chars);
    }

    public static void writeStringToOutput(String string, OutputStream outputStream) throws IOException {
        if(string != null && outputStream != null) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, HTTPConstants.UTF_8));
            writer.write(string);
            writer.flush();
            writer.close();
            outputStream.close();
        }
    }

    public static String getPriceFormat(double d){
        NumberFormat format = new DecimalFormat("#0.00");
        return format.format(d);
    }

    public static String getPriceFormat(String s){
        double d = Double.parseDouble(s);
        NumberFormat format = new DecimalFormat("#0.00");
        return format.format(d);
    }

    public static String removeAsterisk(String s){
        String result = s;
        if(s.contains("*")){
            result = s.replace("*","");
        }
        return result;
    }

    public static String checkDigitsAfterPoint(String text){
        String result = text;
        int pointPos = text.indexOf(".");
        if(pointPos > -1){
            String afterPoint = text.substring(pointPos);
            if(afterPoint.length() > 3){
                result = text.substring(0, pointPos + 3);
            }
        }
        return result;
    }

    public static String readStringFromInput(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }

    public static String toBase64Encode(String source){
        String encodeString = null;
        try {
            byte[] data = source.getBytes(HTTPConstants.UTF_8);
            encodeString = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return encodeString;
    }

    public static String fromBase64Decode(String source){
        String decodeString = null;
        try {
            byte[] data = Base64.decode(source, Base64.DEFAULT);
            decodeString = new String(data, HTTPConstants.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeString;
    }

    public static String toReadableCreditCardName(String cardName){
        char[] cardNameSymbols = cardName.toCharArray();
        boolean isStart = true;
        for(int i = 0; i < cardNameSymbols.length; i++){
           if(isStart){
               isStart = false;
               char curChar = Character.toUpperCase(cardNameSymbols[i]);
               cardNameSymbols[i] = curChar;
           } else if(cardNameSymbols[i] == '_'){
               isStart = true;
               cardNameSymbols[i] = ' ';
           }
        }
        return new String(cardNameSymbols);
    }

    public static List<String> toReadableCardNameList(List<String> cardsList){
        List<String> readableCardList = new ArrayList<>();
        for(String card: cardsList){
            readableCardList.add(toReadableCreditCardName(card));
        }
        return readableCardList;
    }

    public static String toWritableCreditCardName(String cardName){
        char[] cardNameSymbols = cardName.toCharArray();
        for(int i = 0; i < cardNameSymbols.length; i++){
            if(Character.isUpperCase(cardNameSymbols[i])){
                char curChar = Character.toLowerCase(cardNameSymbols[i]);
                cardNameSymbols[i] = curChar;
            } else if(cardNameSymbols[i] == ' '){
                cardNameSymbols[i] = '_';
            }
        }
        return new String(cardNameSymbols);
    }

    public static String asterixedCreditCard(String cardNumber){
        StringBuilder builder = new StringBuilder();
        builder.append("**** **** **** ");
        builder.append(cardNumber.substring(12));
        return builder.toString();
    }
}
