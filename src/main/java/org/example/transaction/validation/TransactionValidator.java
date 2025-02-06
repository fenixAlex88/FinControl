package org.example.transaction.validation;

import java.math.BigDecimal;

/**
 * An interface for validating transactions.
 */
public interface TransactionValidator {

    /**
     * Validates whether the specified amount is acceptable for a transaction.
     *
     * @param amount the amount to validate.
     * @return true if the amount is valid, false otherwise.
     */
    boolean validate(BigDecimal amount);
}

