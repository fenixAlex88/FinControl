package org.example.transaction;

import org.example.model.BankAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * A class for processing transactions on a list of bank accounts.
 */
public class TransactionProcessor {
    /**
     * Processes a withdrawal transaction for each account in the list.
     *
     * @param accounts the list of bank accounts.
     * @param amount   the amount to withdraw.
     */
    public void processTransaction(List<BankAccount> accounts, BigDecimal amount) {
        for (BankAccount account : accounts) {
            account.withdraw(amount);
        }
    }
}
