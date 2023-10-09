package vendingmachine.currency;

public class KoreaWon extends Currency {
    public KoreaWon(int value) {
        super(value);
    }

    @Override
    public String toString() {
        return this.value + "원";
    }
}
