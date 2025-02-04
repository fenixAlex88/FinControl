package org.example.transaction.fees;

import java.math.BigDecimal;

/**
 * An interface for applying fees to transactions.
 */
public interface TransactionFee {
    /**
     * Applies a fee to the specified amount.
     *
     * @param amount the amount to which the fee is applied.
     * @return the total amount including the fee.
     */
    BigDecimal applyFee(BigDecimal amount);
}
