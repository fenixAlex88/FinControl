package org.example.model;

import org.example.utils.MoneyUtils;

import java.math.BigDecimal;

/**
 * An abstract class representing a bank account.
 * Provides common fields and methods for all types of accounts.
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected BigDecimal balance;
    protected String accountHolder;

    /**
     * Constructs a new BankAccount with the specified account number, balance, and account holder.
     *
     * @param accountNumber the unique identifier for the account.
     * @param balance       the initial balance of the account.
     * @param accountHolder the name of the account holder.
     * @throws IllegalArgumentException if accountNumber, balance, or accountHolder is null.
     */
    protected BankAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        if (accountNumber == null || balance == null || accountHolder == null) {
            throw new IllegalArgumentException("Account number, balance, and account holder cannot be null");
        }
        this.accountNumber = accountNumber;
        this.balance = MoneyUtils.round(balance);
        this.accountHolder = accountHolder;
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw.
     */
    public abstract void withdraw(BigDecimal amount);

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit.
     */
    void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = MoneyUtils.round(balance.add(amount));
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance.
     */
    public BigDecimal getBalance() {
        return MoneyUtils.round(balance);
    }

    /**
     * A generic builder class for creating instances of BankAccount.
     *
     * @param <T> the type of the builder subclass
     */
    public static abstract class Builder<T extends Builder<T>> {
        protected String accountNumber;
        protected BigDecimal balance;
        protected String accountHolder;

        /**
         * Sets the account number for the bank account.
         *
         * @param accountNumber the unique identifier for the account
         * @return the current Builder instance
         */
        public T setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return self();
        }

        /**
         * Sets the initial balance for the bank account.
         *
         * @param balance the initial balance of the account
         * @return the current Builder instance
         */
        public T setBalance(double balance) {
            this.balance = MoneyUtils.round(BigDecimal.valueOf(balance));
            return self();
        }

        /**
         * Sets the initial balance for the bank account.
         *
         * @param balance the initial balance of the account
         * @return the current Builder instance
         */
        public T setBalance(BigDecimal balance) {
            this.balance = MoneyUtils.round(balance);
            return self();
        }

        /**
         * Sets the account holder's name for the bank account.
         *
         * @param accountHolder the name of the account holder
         * @return the current Builder instance
         */
        public T setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
            return self();
        }

        /**
         * Returns the current Builder instance.
         *
         * @return the current Builder instance
         */
        protected abstract T self();

        /**
         * Builds and returns a new instance of BankAccount.
         *
         * @return a new instance of BankAccount
         */
        protected abstract BankAccount build();
    }
}
