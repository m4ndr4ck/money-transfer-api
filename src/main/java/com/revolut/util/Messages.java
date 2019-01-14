package com.revolut.util;

import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle messages = ResourceBundle.getBundle("Messages");

    public static final String SENDER_NOT_FOUND = messages.getString("SENDER_NOT_FOUND");
    public static final String RECEIVER_NOT_FOUND = messages.getString("RECEIVER_NOT_FOUND");
    public static final String APPLICATION_JSON = messages.getString("APPLICATION_JSON");
    public static final String MONEY_TRANSFER_SUCCESS = messages.getString("MONEY_TRANSFER_SUCCESS");
    public static final String NO_BALANCE = messages.getString("NO_BALANCE");
    public static final String REQUEST_ERROR = messages.getString("REQUEST_ERROR");
    public static final int HTTP_GOOD_REQUEST = 200;
    public static final int HTTP_BAD_REQUEST = 400;
}
