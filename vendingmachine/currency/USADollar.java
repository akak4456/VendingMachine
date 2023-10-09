package vendingmachine.currency;

public class USADollar extends Currency {
    public USADollar(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return this.value + "달러";
    }
}
