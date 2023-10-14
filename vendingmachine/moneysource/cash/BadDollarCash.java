package vendingmachine.moneysource.cash;

import vendingmachine.Currency;

public class BadDollarCash extends Cash {
    public BadDollarCash(int expressionValue) {
        super(Currency.USD, expressionValue);
    }
}
