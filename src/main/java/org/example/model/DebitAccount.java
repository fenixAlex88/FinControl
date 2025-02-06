package org.example.model;

import org.example.config.Constants;
import org.example.transaction.validation.TransactionValidator;
import org.example.utils.MoneyUtils;

import java.math.BigDecimal;

/**
 * A class representing a debit account. Allows withdrawals only if sufficient funds are available.
 */
public class DebitAccount extends BankAccount implements TransactionValidator {


    private DebitAccount(DebitAccountBuilder builder) {
        super(builder.accountNumber, MoneyUtils.round(builder.balance), builder.accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Withdrawal amount cannot be negative");
            return;
        }
        if (!validate(amount)) {
            System.out.printf("Transaction limit is %.2f \n", Constants.DEBIT_WITHDRAW_LIMIT);
            return;
        }
        if (balance.compareTo(amount) >= 0) {
            balance = MoneyUtils.round(balance.subtract(amount));
            System.out.println("New balance is " + MoneyUtils.format(balance));
        } else {
            System.out.println("Insufficient funds in debit account");
        }
    }

    @Override
    public boolean validate(BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(Constants.DEBIT_WITHDRAW_LIMIT)) <= 0;
    }

    /**
     * Builder class for creating instances of DebitAccount.
     */
    public static class DebitAccountBuilder extends Builder<DebitAccountBuilder> {
        @Override
        protected DebitAccountBuilder self() {
            return this;
        }

        @Override
        public DebitAccount build() {
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "Debit account cannot have negative initial balance. Provided: " + MoneyUtils.format(balance)
                );
            }
            return new DebitAccount(this);
        }
    }
}
