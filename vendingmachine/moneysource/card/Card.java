package vendingmachine.moneysource.card;

import vendingmachine.currency.Currency;
import vendingmachine.currency.NotSameCurrencyKindException;
import vendingmachine.moneysource.MoneySource;

public abstract class Card extends MoneySource {
    public abstract boolean pay(Currency currency) throws NotSameCurrencyKindException;
}
