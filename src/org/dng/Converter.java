package org.dng;

public class Converter {
    public static void toConvert(BankAccount bankAccount, CurrencyE currency) {
        if (bankAccount.getCurrency() == currency)
            return;

        //at firs let`s convert to usd, after convert to the selected currency
        double currentAmount = bankAccount.getBalance();
        if (bankAccount.getCurrency() != CurrencyE.USD) {
            currentAmount = bankAccount.getBalance() * bankAccount.getCurrency().getRate();
        }

        //begin transaction ))
        bankAccount.setCurrency(currency);
        bankAccount.setBalance( (double) ( (int)(currentAmount * 100 / currency.getRate()) )/100 );
        //commit transaction ))

    }
}
