package vendingmachine.paymentmachine;

import vendingmachine.currency.Currency;
import vendingmachine.currency.NotSameCurrencyKindException;
import vendingmachine.moneysource.MoneySource;

public interface PaymentMachine<S extends MoneySource> {
    boolean receive(S source);

    boolean pay(Currency currency);

    S change();
}
