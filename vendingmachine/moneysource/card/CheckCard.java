package vendingmachine.moneysource.card;

import vendingmachine.Currency;

public class CheckCard extends Card {

    private int remainAmount;

    public CheckCard(Currency currency, int remainAmount) {
        super(currency);
        this.remainAmount = remainAmount;
    }

    @Override
    public boolean pay(Currency currency, int paidValue) {
        if(this.currency != currency) {
            return false;
        }
        if(this.remainAmount >= paidValue) {
            this.remainAmount -= paidValue;
        }
        return true;
    }
}
