package org.dng;

@FunctionalInterface
public interface ConvertableI {
    void convert(BankAccount bankAccount, CurrencyE currency);
}
