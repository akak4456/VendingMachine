package vendingmachine.moneysource.card;

import vendingmachine.Currency;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CreditCard extends Card {


    public CreditCard(Currency currency, int remainAmount, int underBound) {
        super(currency, remainAmount, underBound);
    }

}
