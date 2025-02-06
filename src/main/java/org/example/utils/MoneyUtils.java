package org.example.utils;

import org.example.config.Constants;

import java.math.BigDecimal;

public class MoneyUtils {
    /**
     * Rounds the value to 2 decimal places.
     *
     * @param value the value to round
     * @return the rounded value
     */
    public static BigDecimal round(BigDecimal value) {
        return value.setScale(Constants.SCALE, Constants.ROUNDING_MODE);
    }

    /**
     * Formats the value for output.
     *
     * @param value the value to format
     * @return the formatted string
     */
    public static String format(BigDecimal value) {
        return String.format("%,.2f", value);
    }
}
