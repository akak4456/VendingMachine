package vendingmachine.moneysource.cash;

import vendingmachine.currency.Currency;
import vendingmachine.moneysource.MoneySource;

public abstract class Cash extends MoneySource implements Cloneable {
    protected Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public Cash clone() {
        try {
            return (Cash) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
