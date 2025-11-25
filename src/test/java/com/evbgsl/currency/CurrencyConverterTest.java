package com.evbgsl.currency;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void convertRubToUsd() {
        CurrencyConverter converter = new CurrencyConverter();

        BigDecimal result = converter.convert(BigDecimal.valueOf(900), Currency.RUB, Currency.USD);

        assertEquals(0, result.compareTo(BigDecimal.valueOf(10.0)));
    }

    @Test
    void convertUsdToEur() {
        CurrencyConverter converter = new CurrencyConverter();

        BigDecimal result = converter.convert(BigDecimal.valueOf(9), Currency.USD, Currency.EUR);

        // 9 USD * 90 = 810 RUB; 810 / 100 = 8.1 EUR
        assertEquals(0, result.compareTo(BigDecimal.valueOf(8.1)));
    }

    @Test
    void convertSameCurrencyReturnsSameAmount() {
        CurrencyConverter converter = new CurrencyConverter();

        BigDecimal amount = BigDecimal.valueOf(123.45);
        BigDecimal result = converter.convert(amount, Currency.EUR, Currency.EUR);

        assertEquals(0, result.compareTo(amount));
    }

    @Test
    void negativeAmountShouldThrowException() {
        CurrencyConverter converter = new CurrencyConverter();

        assertThrows(IllegalArgumentException.class, () ->
                converter.convert(BigDecimal.valueOf(-1), Currency.RUB, Currency.USD)
        );
    }

    @Test
    void nullsShouldThrowException() {
        CurrencyConverter converter = new CurrencyConverter();

        assertThrows(IllegalArgumentException.class, () ->
                converter.convert(null, Currency.RUB, Currency.USD)
        );
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert(BigDecimal.ONE, null, Currency.USD)
        );
        assertThrows(IllegalArgumentException.class, () ->
                converter.convert(BigDecimal.ONE, Currency.RUB, null)
        );
    }
}
