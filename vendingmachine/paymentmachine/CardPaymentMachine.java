package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.card.Card;

public class CardPaymentMachine implements PaymentMachine<Card> {
    Card source = null;

    @Override
    public boolean receive(Card source) {
        this.source = source;
        return true;
    }

    @Override
    public boolean pay(Currency currency, int paidValue) {
        if (source == null) {
            return false;
        }
        source.pay(currency, paidValue);
        return true;
    }

    @Override
    public Card change() {
        return null;
    }
}
