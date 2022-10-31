package com.eda.echannel.util;

public class InputValidatorUtil {
    public static String validateStringProperty(String message, String propertyValue) throws Exception {

        if (propertyValue == null) {
            throw new Exception(message);
        }
        propertyValue = propertyValue.trim();
        if (propertyValue.isEmpty()) {
            throw new Exception(message);
        }
        return propertyValue;
    }

    public static String validateStringProperty(String message, String propertyValue, String property, int maxLen) throws Exception {

        if (propertyValue == null) {
            throw new Exception(message+" null");
        }
        propertyValue = propertyValue.trim();
        if (propertyValue.isEmpty()) {
            throw new Exception(message+" empty");
        }

        if (propertyValue.length() > maxLen) {
            throw new Exception("You have reached your maximum limit of characters allowed. Property name : " + property + " , maximum limit : " + maxLen);
        }
        return propertyValue;
    }

}
