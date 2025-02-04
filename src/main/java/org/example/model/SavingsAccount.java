package org.example.model;

import org.example.config.Constants;
import org.example.account.features.InterestBearing;
import org.example.utils.MoneyUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A class representing a savings account. Allows withdrawals and applies monthly interest.
 */
public class SavingsAccount extends BankAccount implements InterestBearing {

    private SavingsAccount(SavingsAccountBuilder builder) {
        super(builder.accountNumber, MoneyUtils.round(builder.balance), builder.accountHolder);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Withdrawal amount cannot be negative");
            return;
        }
        if (balance.compareTo(amount) >= 0) {
            balance = MoneyUtils.round(balance.subtract(amount));
            System.out.println("New balance is " + MoneyUtils.format(balance));
        } else {
            System.out.println("Insufficient funds in savings account");
        }
    }

    @Override
    public void applyInterest() {
        BigDecimal interest = balance.multiply(BigDecimal.valueOf(Constants.INTEREST_RATE_PERCENT / 100.0))
                .divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
        balance = MoneyUtils.round(balance.add(interest));
    }

    /**
     * Builder class for creating instances of DebitAccount.
     */
    public static class SavingsAccountBuilder extends Builder<SavingsAccountBuilder> {
        @Override
        protected SavingsAccountBuilder self() {
            return this;
        }

        @Override
        public SavingsAccount build() {
            if (balance.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                        "Savings account cannot have negative initial balance. Provided: " + MoneyUtils.format(balance)
                );
            }
            return new SavingsAccount(this);
        }
    }
}
