package org.example.model;

import org.example.config.TestConstants;
import org.example.config.Constants;
import org.example.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingsAccountTest {
    private SavingsAccount acc;

    @BeforeEach
    public void setUp() {
        acc = TestDataGenerator.buildSavingsAccountWithBalance(TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE);
    }

    @Test
    @DisplayName("Test withdrawal within balance limit")
    public void testSavingsAccountWithdrawWithinBalance() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_SMALL));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL
        ));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should decrease by " + TestConstants.WITHDRAW_AMOUNT_SMALL);
    }

    @Test
    @DisplayName("Test withdrawal exceeding balance")
    public void testSavingsAccountWithdrawExceedingBalance() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_LARGE));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged when withdrawing more than balance");
    }

    @Test
    @DisplayName("Test withdrawal with negative amount")
    public void testSavingsAccountWithdrawNegativeAmount() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.NEGATIVE_WITHDRAW_AMOUNT));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged with negative withdrawal amount");
    }

    @Test
    @DisplayName("Test applying interest")
    public void testSavingsAccountApplyInterest() {
        acc.applyInterest();
        BigDecimal expectedInterest = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE * Constants.INTEREST_RATE_PERCENT / 100 / 12
        ));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE
        )).add(expectedInterest);
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should reflect applied interest rate of " +
                        Constants.INTEREST_RATE_PERCENT + "%");
    }

    @Test
    @DisplayName("Test account creation validation")
    public void testAccountCreationValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                        TestDataGenerator.buildSavingsAccountWithBalance(TestConstants.NEGATIVE_BALANCE),
                "Should throw exception for negative initial balance"
        );
    }
}