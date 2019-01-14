package com.revolut.business;

import com.revolut.entity.Account;
import com.revolut.entity.Transaction;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiPredicate;
import static com.revolut.util.Messages.NO_BALANCE;
/**
 * Provides business operations for money transfer
 *
 * @author m4ndr4ck
 */
public interface Operations {
    /**
     * Check if account exists
     *
     */
    static BiPredicate<Integer, Map<Integer, Account>> accountExists(){
        return (accountId, accounts)-> accounts.containsKey(accountId);
    }
    /**
     * Check if account has enough balance
     *
     */
    static BiPredicate<Account, BigDecimal> checkBalance(){
        return (sender, value) -> sender.getBalance().compareTo(value)<0;
    }
    /**
     * Try to do the money transfer by 1) checking balance and 2) performing transaction
     *
     * @param txSender Money sender
     * @param txReceiver Money receiver
     * @param value value to send
     */
    static boolean sendMoney(Account txSender, Account txReceiver, BigDecimal value) throws Exception {
        try{
            if(checkBalance().test(txSender, value)) {
                throw new Exception(NO_BALANCE);
            } else {
                Transaction tx = new Transaction.Builder().sender(txSender).receiver(txReceiver).amount(value).build();

                txSender.setBalance(txSender.getBalance().subtract(value));
                txSender.addTransactions(tx); // store transaction on sender account transactions list

                txReceiver.setBalance(txReceiver.getBalance().add(value));
                txReceiver.addTransactions(tx); // store transaction on receiver account transactions list
                return true;
            }
        } catch (Exception sendMoneyException){
            throw sendMoneyException;
        }
    }
}