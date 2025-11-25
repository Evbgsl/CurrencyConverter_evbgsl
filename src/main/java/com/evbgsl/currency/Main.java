package com.evbgsl.currency;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        BigDecimal amount = BigDecimal.valueOf(100);

        BigDecimal usd = converter.convert(amount, Currency.RUB, Currency.USD);
        System.out.println("100 RUB = " + usd + " USD");
    }
}
