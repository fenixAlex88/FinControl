package org.example.model;

import org.example.config.TestConstants;
import org.example.config.Constants;
import org.example.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditAccountTest {
    private CreditAccount acc;

    @BeforeEach
    public void setUp() {
        acc = TestDataGenerator.buildCreditAccount();
    }

    @Test
    @DisplayName("Test withdrawal within balance limit")
    public void testCreditAccountWithdrawWithinBalance() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_SMALL));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL -
                        TestConstants.WITHDRAW_AMOUNT_SMALL * Constants.FEE_RATE_PERCENT / 100));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should be " + expectedBalance + " after withdrawing " +
                        TestConstants.WITHDRAW_AMOUNT_SMALL + " with " +
                        Constants.FEE_RATE_PERCENT + "% fee");
    }

    @Test
    @DisplayName("Test withdrawal exceeding balance but within credit limit")
    public void testCreditAccountWithdrawWithinCreditLimit() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_LARGE));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_LARGE -
                        TestConstants.WITHDRAW_AMOUNT_LARGE * Constants.FEE_RATE_PERCENT / 100));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should be " + expectedBalance + " after withdrawing " +
                        TestConstants.WITHDRAW_AMOUNT_LARGE + " with " +
                        Constants.FEE_RATE_PERCENT + "% fee within credit limit");
    }

    @Test
    @DisplayName("Test withdrawal exceeding transaction limit")
    public void testCreditAccountWithdrawExceedingLimit() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_EXCEEDING_LIMIT));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should remain " + expectedBalance + " after attempting to withdraw " +
                        TestConstants.WITHDRAW_AMOUNT_EXCEEDING_LIMIT + ", which exceeds the transaction limit");
    }

    @Test
    @DisplayName("Test withdrawal with negative amount")
    public void testCreditAccountWithdrawNegativeAmount() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.NEGATIVE_WITHDRAW_AMOUNT));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should remain " + expectedBalance + " after attempting to withdraw a negative amount");
    }

    @Test
    @DisplayName("Test withdrawal exceeding credit limit")
    public void testCreditAccountWithdrawExceedingCreditLimit() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT));
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT -
                        TestConstants.WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT * Constants.FEE_RATE_PERCENT / 100));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should be " + expectedBalance + " after withdrawing " +
                        TestConstants.WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT +
                        " with " + Constants.FEE_RATE_PERCENT +
                        "% fee, and subsequent withdrawal exceeding the credit limit should not proceed");
    }
}

