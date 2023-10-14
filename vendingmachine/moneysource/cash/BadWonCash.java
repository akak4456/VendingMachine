package vendingmachine.moneysource.cash;

import vendingmachine.Currency;

public class BadWonCash extends Cash {

    public BadWonCash(int expressionValue) {
        super(Currency.KRW, expressionValue);
    }
}
