package vendingmachine.paymentmachine;

import vendingmachine.currency.Currency;
import vendingmachine.currency.NotSameCurrencyKindException;
import vendingmachine.moneysource.card.Card;

public class CardPaymentMachine implements PaymentMachine<Card> {
    Card source = null;

    @Override
    public boolean receive(Card source) {
        this.source = source;
        return true;
    }

    @Override
    public boolean pay(Currency currency) {
        if (source == null) {
            return false;
        }
        try {
            source.pay(currency);
            return true;
        } catch (NotSameCurrencyKindException e) {
            return false;
        }
    }

    @Override
    public Card change() {
        return null;
    }
}
