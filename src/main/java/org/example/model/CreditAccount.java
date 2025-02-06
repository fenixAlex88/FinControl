package org.example.model;

import org.example.config.Constants;
import org.example.transaction.fees.TransactionFee;
import org.example.transaction.validation.TransactionValidator;
import org.example.utils.MoneyUtils;

import java.math.BigDecimal;

/**
 * A class representing a credit account. Allows withdrawals up to a specified credit limit.
 * Applies a fee for each withdrawal.
 */
public class CreditAccount extends BankAccount implements TransactionFee, TransactionValidator {


    private CreditAccount(CreditAccountBuilder builder) {
        super(builder.accountNumber, MoneyUtils.round(builder.balance), builder.accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Withdrawal amount cannot be negative");
            return;
        }
        if (!validate(amount)) {
            System.out.printf("Transaction limit is %.2f \n", Constants.CREDIT_WITHDRAW_LIMIT);
            return;
        }
        BigDecimal newBalance = MoneyUtils.round(balance.subtract(applyFee(amount)));
        if (newBalance.compareTo(BigDecimal.valueOf(Constants.CREDIT_LIMIT)) >= 0) {
            balance = newBalance;
            System.out.println("New balance is " + MoneyUtils.format(balance));
        } else {
            System.out.println("Withdrawal exceeds credit limit");
        }
    }

    @Override
    public BigDecimal applyFee(BigDecimal amount) {
        BigDecimal fee = MoneyUtils.round(amount.multiply(BigDecimal.valueOf(Constants.FEE_RATE_PERCENT / 100.0)));
        return MoneyUtils.round(amount.add(fee));
    }

    @Override
    public boolean validate(BigDecimal amount) {
        return amount.compareTo(BigDecimal.valueOf(Constants.CREDIT_WITHDRAW_LIMIT)) <= 0;
    }

    /**
     * Builder class for creating instances of CreditAccount.
     */
    public static class CreditAccountBuilder extends Builder<CreditAccountBuilder> {
        @Override
        protected CreditAccountBuilder self() {
            return this;
        }

        @Override
        public CreditAccount build() {
            return new CreditAccount(this);
        }
    }
}
