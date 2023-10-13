package vendingmachine.moneysource.card;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

public abstract class Card extends MoneySource {
    public Card(Currency currency) {
        super(currency);
    }

    public abstract boolean pay(Currency currency, int paidValue);
}
