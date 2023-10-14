package vendingmachine.moneysource;

import vendingmachine.Currency;

/**
 * Cash, Card 를 추상화한 클래스입니다.
 * 어떤 화폐를 쓰는지(원화, 달러 등) 그 화폐의 가치는 얼마인지가 결정됩니다.
 * MoneySource 끼리 더하거나 조건이 맞으면 빼는 기능이 있습니다.
 */
public abstract class MoneySource {
    protected Currency currency;
    protected int expressionValue;

    public MoneySource(Currency currency, int expressionValue) {
        this.currency = currency;
        this.expressionValue = expressionValue;
    }

    public boolean addExpressionValue(MoneySource source) {
        return addExpressionValue(source.currency, source.expressionValue);
    }

    public boolean addExpressionValue(Currency currency, int expressionValue) {
        if (this.currency != currency) {
            return false;
        }
        this.expressionValue += expressionValue;
        return true;
    }

    public boolean subtractExpressionValueIfPossible(MoneySource source, int underBound) {
        return subtractExpressionValueIfPossible(source.currency, source.expressionValue, underBound);
    }

    public boolean subtractExpressionValueIfPossible(Currency currency, int expressionValue, int underBound) {
        if (this.currency != currency) {
            return false;
        }
        if (this.expressionValue - expressionValue < underBound) {
            return false;
        }
        this.expressionValue -= expressionValue;
        return true;
    }

    public boolean isGreaterThanOrEqualsTo(Currency currency, int expressionValue) {
        if (this.currency != currency) {
            return false;
        }
        return this.expressionValue >= expressionValue;
    }

    public Currency getCurrency() {return currency;}

    public int getExpressionValue() {
        return this.expressionValue;
    }
}
