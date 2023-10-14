package vendingmachine.moneysource.cash;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

/**
 * Cash 는 현금을 의미하며 원화, 달러 등이 있고
 * 현금이기는 하지만 상태가 나빠(찢어짐 등) 받아들일 수 없는 현금도 있습니다.
 */
public abstract class Cash extends MoneySource {

    public Cash(Currency currency, int expressionValue) {
        super(currency, expressionValue);
    }

    @Override
    public String toString() {
        return expressionValue + currency.getUnit();
    }

}
