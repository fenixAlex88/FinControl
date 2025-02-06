package org.example.transaction;

import org.example.config.TestConstants;
import org.example.config.Constants;
import org.example.model.*;
import org.example.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionProcessorTest {
    private TransactionProcessor processor;
    private DebitAccount debitAccount;
    private CreditAccount creditAccount;
    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setUp() {
        processor = new TransactionProcessor();
        debitAccount = TestDataGenerator.buildDebitAccount();
        creditAccount = TestDataGenerator.buildCreditAccount();
        savingsAccount = TestDataGenerator.buildSavingsAccount();
    }

    @Test
    @DisplayName("Test processing transaction for multiple accounts")
    public void testProcessTransactionMultipleAccounts() {
        List<BankAccount> accounts = Arrays.asList(debitAccount, creditAccount, savingsAccount);
        processor.processTransaction(accounts, BigDecimal.valueOf(TestConstants.WITHDRAW_AMOUNT_SMALL));

        BigDecimal expectedDebitBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_DEBIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL));
        BigDecimal expectedCreditBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_CREDIT_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL -
                        TestConstants.WITHDRAW_AMOUNT_SMALL * Constants.FEE_RATE_PERCENT / 100));
        BigDecimal expectedSavingsBalance = MoneyUtils.round(BigDecimal.valueOf(
                TestConstants.DEFAULT_SAVINGS_ACCOUNT_BALANCE - TestConstants.WITHDRAW_AMOUNT_SMALL));

        assertEquals(expectedDebitBalance, debitAccount.getBalance(),
                "Debit account balance should be " + expectedDebitBalance +
                        " after withdrawing " + TestConstants.WITHDRAW_AMOUNT_SMALL);
        assertEquals(expectedCreditBalance, creditAccount.getBalance(),
                "Credit account balance should be " + expectedCreditBalance +
                        " after withdrawing " + TestConstants.WITHDRAW_AMOUNT_SMALL +
                        " with " + Constants.FEE_RATE_PERCENT + "% fee");
        assertEquals(expectedSavingsBalance, savingsAccount.getBalance(),
                "Savings account balance should be " + expectedSavingsBalance +
                        " after withdrawing " + TestConstants.WITHDRAW_AMOUNT_SMALL);
    }
}
