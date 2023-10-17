package vendingmachine;

import vendingmachine.itemselector.ItemSelector;
import vendingmachine.itemselector.SelectorDTO;
import vendingmachine.material.discrete.Metal;
import vendingmachine.material.discrete.Paper;
import vendingmachine.paymentmachine.CashPaymentMachine;
import vendingmachine.people.NormalPeople;

public abstract class VendingMachine<S extends SelectorDTO> {
    protected CashPaymentMachine cashPaymentMachine;

    protected ItemSelector<S> itemSelector;

    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            ItemSelector<S> itemSelector
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.itemSelector = itemSelector;
    }

    protected abstract void printWhenPushPaperSuccess();

    protected abstract void printWhenPushPaperFail();

    protected abstract void printWhenPushMetalSuccess();

    protected abstract void printWhenPushMetalFail();

    protected abstract void printWhenIsAllChange();

    protected abstract void printWhenIsNotAllChange();

    public void pushPaper(NormalPeople normalPeople, Paper paper) {
        if (normalPeople.containsPaper(paper) && cashPaymentMachine.receivePaper(paper)) {
            normalPeople.removePaper(paper);
            printWhenPushPaperSuccess();
        } else {
            printWhenPushPaperFail();
        }
        itemSelector.showSelector(this);
    }

    public void pushMetal(NormalPeople normalPeople, Metal metal) {
        if(normalPeople.containsMetal(metal) && cashPaymentMachine.receiveMetal(metal)) {
            normalPeople.removeMetal(metal);
            printWhenPushMetalSuccess();
        } else {
            printWhenPushMetalFail();
        }
        itemSelector.showSelector(this);
    }

    public void change(NormalPeople normalPeople) {
        normalPeople.addAllPaper(cashPaymentMachine.changeByCash());
        normalPeople.addAllMetal(cashPaymentMachine.changeByCoin());
        if(cashPaymentMachine.isAllChange()) {
            printWhenIsAllChange();
        } else {
            printWhenIsNotAllChange();
        }
        itemSelector.showSelector(this);
    }

    public boolean isCashPaymentMachineVOGreaterThanOrEqualsTo(VendingMachineVO vo) {
        return cashPaymentMachine.isUserVOGreaterThanOrEqualsTo(vo);
    }
}
