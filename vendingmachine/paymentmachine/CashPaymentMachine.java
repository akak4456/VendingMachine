package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.cash.Cash;

public class CashPaymentMachine implements PaymentMachine<Cash> {
    private Cash source = null;

    private final Class<? extends Cash> availableClass;

    public CashPaymentMachine(Class<? extends Cash> availableClass) {
        this.availableClass = availableClass;
    }

    public String getCurrentCashString() {
        return source.toString();
    }

    public boolean isSourceGreaterThanOrEqualsTo(Currency currency, int paidValue) {
        return source.isGreaterThanOrEqualsTo(currency, paidValue);
    }

    @Override
    public boolean receive(Cash source) {
        if (availableClass != source.getClass()) {
            return false;
        }
        if (this.source == null) {
            this.source = source;
            return true;
        }
        return this.source.addExpressionValue(source);
    }

    @Override
    public boolean pay(Currency currency, int paidValue) {
        if (source == null) {
            return false;
        }
        return this.source.subtractExpressionValueIfPossible(currency, paidValue, 0);
    }

    @Override
    public Cash change() {
        Cash returnSource = source;
        source = null;
        return returnSource;
    }
}
