package vendingmachine.moneysource.card;

import vendingmachine.Currency;

public class CheckCard extends Card {

    public CheckCard(Currency currency, int remainAmount) {
        super(currency, remainAmount,0);
    }

}
