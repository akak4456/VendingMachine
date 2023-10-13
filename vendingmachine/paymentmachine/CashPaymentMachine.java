package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.cash.Cash;

public class CashPaymentMachine implements PaymentMachine<Cash> {
    Cash source = null;

    Class<? extends Cash> availableClass;

    public CashPaymentMachine(Class<? extends Cash> availableClass) {
        this.availableClass = availableClass;
    }

    public int getTotalMoney() {
        if (source == null) {
            return 0;
        }
        return source.getExpressionValue();
    }

    @Override
    public boolean receive(Cash source) {
        if(this.source == null) {
            this.source = source;
            return true;
        }
        if (availableClass != source.getClass()) {
            return false;
        }
        return this.source.addCash(source);
    }

    @Override
    public boolean pay(Currency currency, int paidValue) {
        if (source == null) {
            return false;
        }
        if (this.source.isGreaterThanOrEqualsTo(source)) {
            this.source.subtractCash(source);
            return true;
        }
        return false;
    }

    @Override
    public Cash change() {
        return source;
    }
}
