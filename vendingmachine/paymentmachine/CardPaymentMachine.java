package vendingmachine.paymentmachine;

import vendingmachine.Currency;
import vendingmachine.moneysource.card.Card;

public class CardPaymentMachine implements PaymentMachine<Card> {
    private Card source = null;

    private Currency availableCurrency;

    public CardPaymentMachine(Currency availableCurrency) {
        this.availableCurrency = availableCurrency;
    }

    @Override
    public boolean receive(Card source) {
        if(availableCurrency != source.getCurrency()) {
            return false;
        }
        this.source = source;
        return true;
    }

    @Override
    public boolean pay(Currency currency, int paidValue) {
        if (source == null) {
            return false;
        }
        return source.subtractExpressionValueIfPossible(currency, paidValue, source.getUnderBound());
    }

    @Override
    public Card change() {
        Card returnSource = source;
        source = null;
        return returnSource;
    }
}
