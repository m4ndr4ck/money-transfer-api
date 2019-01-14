package com.revolut;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.revolut.business.Operations;
import com.revolut.entity.Account;
import com.revolut.util.Helpers;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;
/**
 * Unit tests
 */
public class UnitTests
{
    /**
     * Sample account data
     */
    Map<Integer, Account> accounts = Helpers.sampleData();

    @Test
    public void checkAccountExistence()
    {
        assertTrue(Operations.accountExists().test(1, accounts)); // Account id 1
    }

    @Test
    public void checkBalance()
    {
        assertFalse(Operations.checkBalance().test(accounts.get(2), new BigDecimal(10))); // Account id 2, balance 10
    }

    @Test
    public void sendMoney() throws Exception
    {
        Account sender = accounts.get(3); // Current balance is 820.56
        Account receiver = accounts.get(1); // Current balance is 1460
        BigDecimal value = new BigDecimal(200);

        System.out.println("-- Send Money Test (Sending 200) --\n");

        System.out.println("## Before Transaction ##" +
                        "\nSender balance: "+sender.getBalance()+
                        "\nReceiver balance: "+Double.valueOf(receiver.getBalance().toString()));

        assertTrue(Operations.sendMoney(sender, receiver, value));

        System.out.println("## After Transaction ##" +
                "\nSender balance: "+sender.getBalance()+
                "\nReceiver balance: "+Double.valueOf(receiver.getBalance().toString()));

    }
}
