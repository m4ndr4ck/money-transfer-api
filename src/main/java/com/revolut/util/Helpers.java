package com.revolut.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonParseException;
import com.revolut.entity.Account;

import static com.revolut.util.Messages.REQUEST_ERROR;
/**
 * Helper class that provides sample data and prepares json responses
 *
 * @author m4ndr4ck
 */
public class Helpers {
    /**
     * Store sample data in memory
     *
     */
    public static Map<Integer, Account> sampleData(){
        Map<Integer, Account> accounts = new HashMap<>();
        //Initial params: id, name, balance
        accounts.put(000001, new Account.Builder(000001, "Davi", 820.56).build());
        accounts.put(000002, new Account.Builder(000002, "Bruno", 28320.13).build());
        accounts.put(000003, new Account.Builder(000002, "Denis", 1460).build());
        return accounts;
    }
    /**
     * Simple validation on post request parameters
     *
     * @param senderId
     * @param receiverId
     * @param value
     */
    public static boolean validateRequest(Object senderId, Object receiverId, String value) throws JsonParseException {
        if(!(senderId instanceof Integer) || !(receiverId instanceof Integer))
            throw new JsonParseException(REQUEST_ERROR);
        if(senderId == null || receiverId == null)
            throw new JsonParseException(REQUEST_ERROR);
        if(senderId == receiverId)
            throw new JsonParseException(REQUEST_ERROR);
        try {
            if (value == null || Double.parseDouble(value) <= 0)
                throw new JsonParseException(REQUEST_ERROR);
        } catch (NumberFormatException e){
            throw new JsonParseException(REQUEST_ERROR);
        }
        return false;
    }
}
