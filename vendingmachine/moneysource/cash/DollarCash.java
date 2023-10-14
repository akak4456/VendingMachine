package vendingmachine.moneysource.cash;

import vendingmachine.Currency;

public class DollarCash extends Cash{
    public DollarCash(int expressionValue) {
        super(Currency.USD, expressionValue);
    }
}
