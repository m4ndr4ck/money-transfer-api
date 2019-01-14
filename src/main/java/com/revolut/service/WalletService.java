package com.revolut.service;

import java.math.BigDecimal;
import java.util.Map;

import com.revolut.entity.Account;
import com.revolut.business.Operations;
import com.revolut.util.Helpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import lombok.Data;

import static com.revolut.util.Messages.*;
import static spark.Spark.post;
/**
 * WalletService
 *
 * @author m4ndr4ck
 */
public class WalletService implements Operations {
    /**
     * These are the parameters to inform on POST request
     */
    @Data
    public static class TransferPayload {
        private Object senderId;
        private Object receiverId;
        private String value;
    }
    /**
     * Initialize the Money Transfer API to be called on http://localhost:8080/sendMoney
     */
    public static void init() {
        /**
         * Initialize sample data
         */
        Map<Integer, Account> accounts = Helpers.sampleData();
        /**
         * Send money with post request
         */
        post("/sendMoney", (request, response) -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TransferPayload transferPayload = mapper.readValue(request.body(), TransferPayload.class);
                /**
                 * Simple validation on post request parameters
                 */
                Helpers.validateRequest(transferPayload.senderId, transferPayload.receiverId, transferPayload.value);
                /**
                 * Check if sender and receiver account exist on data source
                 */
                String errResp = !Operations.accountExists().test((int)transferPayload.senderId, accounts) ? SENDER_NOT_FOUND :
                        !Operations.accountExists().test((int)transferPayload.receiverId, accounts) ? RECEIVER_NOT_FOUND : "";
                if(!errResp.isEmpty()){
                    response.status(HTTP_BAD_REQUEST);
                    return errResp;
                }
                /**
                 * Perform money transfer
                 */
                Operations.sendMoney(
                        accounts.get(transferPayload.senderId),
                        accounts.get(transferPayload.receiverId),
                        new BigDecimal(Double.valueOf(transferPayload.value)));
                /**
                 * Return success response
                 */
                response.status(HTTP_GOOD_REQUEST);
                return MONEY_TRANSFER_SUCCESS;
            } catch (JsonParseException jpe) {
                response.status(HTTP_BAD_REQUEST);
                return REQUEST_ERROR;
            } catch (Exception e) {
                response.status(HTTP_BAD_REQUEST);
                return e.getMessage();
            }
        });
    }
}