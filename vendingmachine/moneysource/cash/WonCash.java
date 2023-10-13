package vendingmachine.moneysource.cash;

import vendingmachine.Currency;

public class WonCash extends Cash {

    public WonCash(int expressionValue) {
        super(Currency.KRW, expressionValue);
    }
}
