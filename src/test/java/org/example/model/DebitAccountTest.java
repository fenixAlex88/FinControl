package org.example.model;

import org.example.config.TestConstants;
import org.example.config.Constants;
import org.example.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class DebitAccountTest {
    private DebitAccount acc;

    @BeforeEach
    public void setUp() {
        acc = TestDataGenerator.buildDebitAccount();
    }

    @Test
    @DisplayName("Test withdrawal within balance limit")
    public void testDebitAccountWithdrawWithinBalance() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_SMALL));
        BigDecimal expectedBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL
        ));
        assertEquals(expectedBalance, acc.getBalance(),
                "Balance should decrease by " + TestConstants.WITHDRAW_AMOUNT_SMALL);
    }

    @Test
    @DisplayName("Test withdrawal exceeding balance")
    public void testDebitAccountWithdrawExceedingBalance() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_LARGE));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged when withdrawing more than balance");
    }

    @Test
    @DisplayName("Test withdrawal exceeding transaction limit")
    public void testDebitAccountWithdrawExceedingLimit() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_EXCEEDING_LIMIT));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged when exceeding transaction limit of " +
                        Constants.DEBIT_WITHDRAW_LIMIT);
    }

    @Test
    @DisplayName("Test withdrawal with negative amount")
    public void testDebitAccountWithdrawNegativeAmount() {
        acc.withdraw(BigDecimal.valueOf(TestConstants.NEGATIVE_WITHDRAW_AMOUNT));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged with negative withdrawal amount");
    }

    @Test
    @DisplayName("Test valid deposit")
    public void testDepositPositiveAmount() {
        BigDecimal depositAmount = BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_SMALL);
        acc.deposit(depositAmount);
        BigDecimal expected = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE + TestConstants.WITHDRAW_AMOUNT_SMALL
        ));
        assertEquals(expected, acc.getBalance(),
                "Balance should increase by " + TestConstants.WITHDRAW_AMOUNT_SMALL);
    }

    @Test
    @DisplayName("Test invalid deposit")
    public void testDepositNegativeAmount() {
        acc.deposit(BigDecimal.valueOf(TestConstants.NEGATIVE_WITHDRAW_AMOUNT));
        assertEquals(MoneyUtils.round(BigDecimal.valueOf(TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE)),
                acc.getBalance(),
                "Balance should remain unchanged with negative deposit");
    }

    @Test
    @DisplayName("Test account creation validation")
    public void testAccountCreationValidation() {
        assertThrows(IllegalArgumentException.class, () ->
                        TestDataGenerator.buildDebitAccountWithBalance(TestConstants.NEGATIVE_BALANCE),
                "Should throw exception for negative initial balance"
        );
    }
}