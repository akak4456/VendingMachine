package vendingmachine.moneysource.card;

import vendingmachine.currency.Currency;
import vendingmachine.currency.KoreaWon;
import vendingmachine.currency.NotSameCurrencyKindException;

import java.util.List;

public class CreditCard extends Card {
    KoreaWon maxAmount;
    List<KoreaWon> usedList;

    @Override
    public boolean pay(Currency currency) throws NotSameCurrencyKindException {
        KoreaWon totalUsed = new KoreaWon(0);
        for (KoreaWon used : usedList) {
            totalUsed.addCurrency(used);
        }
        totalUsed.addCurrency(currency);
        if (maxAmount.isGreaterThanOrEqualsTo(totalUsed)) {
            usedList.add((KoreaWon) currency);
            return true;
        }
        return false;
    }
}
