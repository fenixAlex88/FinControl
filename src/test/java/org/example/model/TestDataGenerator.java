package org.example.model;

import org.example.config.TestConstants;

/**
 * Utility class for generating test data for various types of bank accounts.
 */
public final class TestDataGenerator {
    private TestDataGenerator() {}

    /**
     * Generates a CreditAccount with default values.
     *
     * @return a CreditAccount instance
     */
    public static CreditAccount buildCreditAccount() {
        return new CreditAccount.CreditAccountBuilder()
                .setAccountNumber(TestConstants.DEFAULT_CREDIT_ACCOUNT_NUMBER)
                .setBalance(TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE)
                .setAccountHolder(TestConstants.DEFAULT_ACCOUNT_HOLDER)
                .build();
    }

    /**
     * Generates a DebitAccount with default values.
     *
     * @return a DebitAccount instance
     */
    public static DebitAccount buildDebitAccount() {
        return new DebitAccount.DebitAccountBuilder()
                .setAccountNumber(TestConstants.DEFAULT_DEBIT_ACCOUNT_NUMBER)
                .setBalance(TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE)
                .setAccountHolder(TestConstants.DEFAULT_ACCOUNT_HOLDER)
                .build();
    }

    /**
     * Generates a DebitAccount with a specified balance.
     *
     * @param initialBalance the initial balance of the account
     * @return a DebitAccount instance
     */
    public static DebitAccount buildDebitAccountWithBalance(double initialBalance) {
        return new DebitAccount.DebitAccountBuilder()
                .setAccountNumber(TestConstants.DEFAULT_DEBIT_ACCOUNT_NUMBER)
                .setBalance(initialBalance)
                .setAccountHolder(TestConstants.DEFAULT_ACCOUNT_HOLDER)
                .build();
    }

    /**
     * Generates a SavingsAccount with default values.
     *
     * @return a SavingsAccount instance
     */
    public static SavingsAccount buildSavingsAccount() {
        return new SavingsAccount.SavingsAccountBuilder()
                .setAccountNumber(TestConstants.DEFAULT_SAVINGS_ACCOUNT_NUMBER)
                .setBalance(TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE)
                .setAccountHolder(TestConstants.DEFAULT_ACCOUNT_HOLDER)
                .build();
    }

    /**
     * Generates a SavingsAccount with a specified balance.
     *
     * @param initialBalance the initial balance of the account
     * @return a SavingsAccount instance
     */
    public static SavingsAccount buildSavingsAccountWithBalance(double initialBalance) {
        return new SavingsAccount.SavingsAccountBuilder()
                .setAccountNumber(TestConstants.DEFAULT_SAVINGS_ACCOUNT_NUMBER)
                .setBalance(initialBalance)
                .setAccountHolder(TestConstants.DEFAULT_ACCOUNT_HOLDER)
                .build();
    }
}
