package vendingmachine.currency;

public abstract class Currency {
    protected int value;

    public Currency(int value) {
        this.value = value;
    }

    public void addCurrency(Currency currency) throws NotSameCurrencyKindException {
        if (getClass() != currency.getClass()) {
            throw new NotSameCurrencyKindException();
        }
        this.value = this.value + currency.value;
    }

    public void subtractCurrency(Currency currency) throws NotSameCurrencyKindException {
        if (getClass() != currency.getClass()) {
            throw new NotSameCurrencyKindException();
        }
        this.value = this.value - currency.value;
    }

    public boolean isGreaterThanOrEqualsTo(Currency currency) throws NotSameCurrencyKindException {
        if (getClass() != currency.getClass()) {
            throw new NotSameCurrencyKindException();
        }
        return this.value >= currency.value;
    }
}
