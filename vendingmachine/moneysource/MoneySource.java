package vendingmachine.moneysource;

import vendingmachine.Currency;

public abstract class MoneySource {
    protected Currency currency;

    public MoneySource(Currency currency) {
        this.currency = currency;
    }
}
