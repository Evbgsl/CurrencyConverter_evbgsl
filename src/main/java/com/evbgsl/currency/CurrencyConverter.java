package com.evbgsl.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

public class CurrencyConverter {

    private final Map<Currency, BigDecimal> rateToRub;

    public CurrencyConverter() {
        rateToRub = new EnumMap<>(Currency.class);
        // Курсы условные, для примера:
        rateToRub.put(Currency.RUB, BigDecimal.valueOf(1.0));
        rateToRub.put(Currency.USD, BigDecimal.valueOf(90.0)); // 1 USD = 90 RUB
        rateToRub.put(Currency.EUR, BigDecimal.valueOf(100.0)); // 1 EUR = 100 RUB
    }

    public BigDecimal convert(BigDecimal amount, Currency from, Currency to) {
        if (amount == null || from == null || to == null) {
            throw new IllegalArgumentException("Amount and currencies must not be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }

        BigDecimal fromRate = rateToRub.get(from);
        BigDecimal toRate = rateToRub.get(to);

        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Unsupported currency");
        }

        // Сначала переведём в RUB, потом в целевую валюту
        BigDecimal inRub = amount.multiply(fromRate);
        BigDecimal result = inRub.divide(toRate, 4, RoundingMode.HALF_UP);

        return result;
    }
}
