package org.dng;

public enum CurrencyE {
    USD(1), EUR(1.2), RUB(0.01);
    private double rate;

    CurrencyE(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
