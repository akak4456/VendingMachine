package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

public interface PaymentMachine<S extends MoneySource> {
    boolean receive(S source);

    boolean pay(Currency currency, int paidValue);

    S change();
}
