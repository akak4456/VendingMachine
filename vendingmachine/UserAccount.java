package vendingmachine;

public class UserAccount {
    private int amount;
    private int underBound;

    public UserAccount(int amount, int underBound) {
        this.amount = amount;
        this.underBound = underBound;
    }

    public boolean subtractAmountIfPossible(int subtracted) {
        if(amount - subtracted >= underBound) {
            this.amount -= subtracted;
            return true;
        }
        return false;
    }
}
