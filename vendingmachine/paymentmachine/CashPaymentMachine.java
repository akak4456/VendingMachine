package vendingmachine.paymentmachine;

import vendingmachine.currency.Currency;
import vendingmachine.currency.NotSameCurrencyKindException;
import vendingmachine.moneysource.cash.Cash;

import java.util.List;

public class CashPaymentMachine implements PaymentMachine<Cash> {
    Cash source = null;

    Class<? extends Currency> availableCash;

    public CashPaymentMachine(Class<? extends Currency> availableCash) {
        this.availableCash = availableCash;
    }

    @Override
    public boolean receive(Cash source) {
        if (source.getCurrency().getClass() != availableCash) {
            return false;
        }
        if (this.source == null) {
            this.source = source.clone();
        } else {
            try {
                this.source.getCurrency().addCurrency(source.getCurrency());
            } catch (NotSameCurrencyKindException e) {
                return false;
            }
        }
        return true;
    }

    public Currency getTotalCurrency() {
        return source.getCurrency();
    }

    @Override
    public boolean pay(Currency currency) {
        if (source == null) {
            return false;
        }
        try {
            if (source.getCurrency().isGreaterThanOrEqualsTo(currency)) {
                source.getCurrency().subtractCurrency(currency);
                return true;
            }
        } catch (NotSameCurrencyKindException e) {
            return false;
        }
        return false;
    }

    @Override
    public Cash change() {
        return source;
    }
}
