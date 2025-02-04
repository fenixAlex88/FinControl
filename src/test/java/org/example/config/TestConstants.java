package org.example.config;

/**
 * Contains test-specific constants and default values for banking application testing.
 * This abstract class serves as a repository for test data configuration.
 *
 * <p>Includes:
 * <ul>
 *   <li>Default account parameters</li>
 *   <li>Test transaction amounts</li>
 *   <li>Boundary values for validation testing</li>
 * </ul>
 */
public abstract class TestConstants {
    /**
     * Default credit account identifier
     */
    public static final String DEFAULT_CREDIT_ACCOUNT_NUMBER = "C01";

    /**
     * Default debit account identifier
     */
    public static final String DEFAULT_DEBIT_ACCOUNT_NUMBER = "D01";

    /**
     * Default savings account identifier
     */
    public static final String DEFAULT_SAVINGS_ACCOUNT_NUMBER = "S01";

    /**
     * Default account holder name for test accounts
     */
    public static final String DEFAULT_ACCOUNT_HOLDER = "Test Holder";

    /**
     * Initial balance for test credit accounts
     */
    public static final double DEFAULT_CREDIT_ACCOUNT_BALANCE = 1_000.00;

    /**
     * Initial balance for test debit accounts
     */
    public static final double DEFAULT_DEBIT_ACCOUNT_BALANCE = 1_500.00;

    /**
     * Initial balance for test savings accounts
     */
    public static final double DEFAULT_SAVINGS_ACCOUNT_BALANCE = 2_000.00;

    /**
     * Typical valid withdrawal amount for positive balance scenarios
     */
    public static final double WITHDRAW_AMOUNT_SMALL = 300;

    /**
     * Large withdrawal amount for credit limit testing
     */
    public static final double WITHDRAW_AMOUNT_LARGE = 2_500;

    /**
     * Amount exceeding maximum transaction limits
     */
    public static final double WITHDRAW_AMOUNT_EXCEEDING_LIMIT = 12_000;

    /**
     * Negative amount for invalid withdrawal tests
     */
    public static final double NEGATIVE_WITHDRAW_AMOUNT = -500;

    /**
     * Negative balance value for account validation tests
     */
    public static final double NEGATIVE_BALANCE = -500;

    /**
     * Amount exceeding credit account withdrawal limit
     */
    public static final double WITHDRAW_AMOUNT_EXCEEDING_CREDIT_LIMIT = 5_000;
}