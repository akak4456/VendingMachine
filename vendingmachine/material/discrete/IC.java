package vendingmachine.material.discrete;

import vendingmachine.UserAccount;

public class IC extends DiscreteRealObject{
    UserAccount account;

    public IC(UserAccount account) {
        this.account = account;
    }

    public UserAccount getAccount() {
        return account;
    }
}
