package vendingmachine.material.discrete;

import vendingmachine.UserAccount;

public class Magnetic extends DiscreteRealObject {
    UserAccount account;

    public Magnetic(UserAccount account) {
        this.account = account;
    }

    public UserAccount getAccount() {
        return account;
    }
}
