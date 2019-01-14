package com.revolut.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
/**
 * Provides Transaction object for money transfer
 *
 * @author m4ndr4ck
 */
public class Transaction {

    private UUID id;
    private Timestamp timestamp;
    private BigDecimal amount;
    private Account sender;
    private Account receiver;

    public static class Builder {

        private UUID id;
        private Timestamp timestamp;
        private BigDecimal amount;
        private Account sender;
        private Account receiver;

        public Builder() {
            this.id = UUID.randomUUID();
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }

        public Builder amount(BigDecimal val){
            amount = val;
            return this;
        }

        public Builder sender(Account val){
            sender = val;
            return this;
        }

        public Builder receiver(Account val){
            receiver = val;
            return this;
        }

        public Transaction build(){
            return new Transaction(this);
        }
    }

    private Transaction(Builder builder){
        id = builder.id;
        timestamp = builder.timestamp;
        amount = builder.amount;
        sender = builder.sender;
        receiver = builder.receiver;
    }

}
