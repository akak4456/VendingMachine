package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

public interface PaymentMachine<S extends MoneySource> {
    /**
     * 결제 기계에서 Money Source 를 받는 함수입니다/
     */
    boolean receive(S source);

    /**
     * 결제 기계에서 Currency paidValue 만큼 값을 지불하는 것입니다.
     */
    boolean pay(Currency currency, int paidValue);

    /**
     * 결제 기계에서 잔돈을 거슬러주는 부분입니다.
     */
    S change();
}
