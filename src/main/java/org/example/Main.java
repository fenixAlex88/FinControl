package org.example;

import org.example.model.BankAccount;
import org.example.model.CreditAccount;
import org.example.model.DebitAccount;
import org.example.model.SavingsAccount;
import org.example.transaction.TransactionProcessor;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BankAccount> accounts = List.of(
                new DebitAccount.DebitAccountBuilder().setAccountNumber("D01").setAccountHolder("Alex").setBalance(BigDecimal.valueOf(10_000)).build(),
                new SavingsAccount.SavingsAccountBuilder().setAccountNumber("S01").setAccountHolder("John").setBalance(5_000).build(),
                new CreditAccount.CreditAccountBuilder().setAccountNumber("C01").setAccountHolder("Boris").setBalance(2_500).build()
        );
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        transactionProcessor.processTransaction(accounts, BigDecimal.valueOf(5_000));
    }
}