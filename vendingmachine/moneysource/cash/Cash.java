package vendingmachine.moneysource.cash;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

public abstract class Cash extends MoneySource implements Cloneable {

    private int expressionValue;

    public Cash(Currency currency, int expressionValue) {
        super(currency);
        this.expressionValue = expressionValue;
    }

    public boolean addCash(Cash otherCash) {
        return addCash(otherCash.currency, otherCash.expressionValue);
    }

    public boolean addCash(Currency currency, int expressionValue) {
        if (this.currency != currency) {
            return false;
        }
        this.expressionValue += expressionValue;
        return true;
    }

    public boolean subtractCash(Cash otherCash) {
        return subtractCash(otherCash.currency, otherCash.expressionValue);
    }

    public boolean subtractCash(Currency currency, int expressionValue) {
        if (this.currency != currency) {
            return false;
        }
        this.expressionValue -= expressionValue;
        return true;
    }

    public boolean isGreaterThanOrEqualsTo(Cash otherCash) {
        return isGreaterThanOrEqualsTo(otherCash.currency, otherCash.expressionValue);
    }

    public boolean isGreaterThanOrEqualsTo(Currency currency, int expressionValue) {
        if (this.currency != currency)
            return false;
        return this.expressionValue >= expressionValue;
    }

    public int getExpressionValue() {
        return this.expressionValue;
    }
}
