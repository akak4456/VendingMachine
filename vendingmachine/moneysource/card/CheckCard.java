package vendingmachine.moneysource.card;

import vendingmachine.currency.Currency;
import vendingmachine.currency.KoreaWon;
import vendingmachine.currency.NotSameCurrencyKindException;

public class CheckCard extends Card {
    private KoreaWon amount;

    public CheckCard(KoreaWon amount) {
        this.amount = amount;
    }

    @Override
    public boolean pay(Currency currency) throws NotSameCurrencyKindException {
        if (amount.isGreaterThanOrEqualsTo(currency)) {
            amount.subtractCurrency(currency);
            return true;
        }
        return false;
    }
}
