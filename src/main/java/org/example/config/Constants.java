package org.example.config;

import java.math.RoundingMode;

/**
 * Contains global constants for the banking application configuration.
 * This class cannot be instantiated and provides only static configuration parameters.
 *
 * <p>Constants include:
 * <ul>
 *   <li>Rounding settings for monetary calculations</li>
 *   <li>Account type-specific limits</li>
 *   <li>Financial rates and thresholds</li>
 * </ul>
 */
public abstract class Constants {
    /**
     * Number of decimal places to maintain in monetary calculations
     */
    public static final int SCALE = 2;

    /**
     * Rounding mode to use for financial operations
     * @see RoundingMode
     */
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * Maximum allowed negative balance for credit accounts
     */
    public static final double CREDIT_LIMIT = -5_000;

    /**
     * Transaction fee percentage applied to credit account withdrawals
     */
    public static final double FEE_RATE_PERCENT = 1;

    /**
     * Maximum single withdrawal amount for credit accounts
     */
    public static final double CREDIT_WITHDRAW_LIMIT = 5_000;

    /**
     * Maximum single withdrawal amount for debit accounts
     */
    public static final double DEBIT_WITHDRAW_LIMIT = 10_000;

    /**
     * Annual interest rate percentage for savings accounts
     */
    public static final double INTEREST_RATE_PERCENT = 5;
}