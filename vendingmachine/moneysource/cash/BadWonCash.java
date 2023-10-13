package vendingmachine.moneysource.cash;

import vendingmachine.Currency;

/**
 * 한국 돈이긴 하지만 찢어진 지폐등 자판기에서는 받을 수 없는 돈을 의미함.
 */
public class BadWonCash extends Cash {

    public BadWonCash(int expressionValue) {
        super(Currency.KRW, expressionValue);
    }
}
