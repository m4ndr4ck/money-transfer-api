package com.revolut.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * Provides Account object for money transfer
 *
 * @author m4ndr4ck
 */
public class Account {

    private int id;
    private String name;
    private BigDecimal balance;
    private List<Transaction> transactions;

    public static class Builder {
        private final int id;
        private final String name;
        private final BigDecimal balance;

        public Builder(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = new BigDecimal(balance);
        }

        public Account build() {
            return new Account(this);
        }

    }

    private Account(Builder builder) {
        id = builder.id;
        name = builder.name;
        balance = builder.balance;
    }

    Account() {

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void addTransactions(Transaction transaction){
        if(transactions==null) transactions = new ArrayList<Transaction>();
        transactions.add(transaction);
    }

}
