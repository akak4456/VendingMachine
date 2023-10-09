package vendingmachine.moneysource.cash;

import vendingmachine.currency.KoreaWon;

public class WonCash extends Cash{
    public WonCash(int won) {
        currency = new KoreaWon(won);
    }
}
