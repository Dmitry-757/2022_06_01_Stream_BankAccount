package org.dng;

import java.util.Objects;

public class BankAccount {
    private String id;
    private CurrencyE currency;
    private double balance;

    public BankAccount(CurrencyE currency, String id, double balance) {
        this.currency = currency;
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public CurrencyE getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyE currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id='" + id + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                '}';
    }

    public void convert(ConvertableI cm, CurrencyE currency){
        cm.convert(this, currency);
    }

}
