package vendingmachine.moneysource.card;

import vendingmachine.Currency;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CreditCard extends Card {

    private int maxAmount;
    List<Integer> usedList;

    public CreditCard(Currency currency, int maxAmount) {
        super(currency);
        this.maxAmount = maxAmount;
    }

    @Override
    public boolean pay(Currency currency, int value) {
        if (this.currency != currency) {
            return false;
        }
        int totalUsed = 0;
        for (int used : usedList) {
            totalUsed += used;
        }
        if (this.maxAmount >= totalUsed) {
            usedList.add(value);
            return true;
        }
        return false;
    }
}
