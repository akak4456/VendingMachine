package vendingmachine;

import vendingmachine.item.VendingMachineItem;
import vendingmachine.moneysource.card.Card;
import vendingmachine.moneysource.cash.Cash;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;

import java.util.ArrayList;
import java.util.List;

public abstract class VendingMachine<I extends VendingMachineItem> {

    protected final CashPaymentMachine cashPaymentMachine;
    protected final CardPaymentMachine cardPaymentMachine;
    protected final List<List<I>> vendingMachineItems;
    protected final List<I> vendingMachineOutletItems;

    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            CardPaymentMachine cardPaymentMachine,
            List<List<I>> vendingMachineItems
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.cardPaymentMachine = cardPaymentMachine;
        this.vendingMachineItems = vendingMachineItems;
        this.vendingMachineOutletItems = new ArrayList<>();
    }

    public abstract void printDisplayItems();

    public abstract void printOutletItems();

    protected abstract void printWhenPushAvailableCash();

    protected abstract void printWhenPushNotAvailableCash();

    protected abstract void printWhenSelectItemCashSuccess();

    protected abstract void printWhenSelectItemCardSuccess();

    protected abstract void printWhenAllPaymentFail();

    protected abstract void printWrongPositionItemSelected();

    protected abstract void printWhenPushAvailableCard();

    protected abstract void printWhenPushNotAvailableCard();

    protected abstract void printWhenChange();

    public void pushCash(Cash cash) {
        if (cashPaymentMachine.receive(cash)) {
            printWhenPushAvailableCash();
        } else {
            printWhenPushNotAvailableCash();
        }
    }

    public void selectItem(int row, int col) {
        if (0 <= row && row < vendingMachineItems.size() && 0 <= col && col < vendingMachineItems.get(row).size()) {
            I selectedItem = vendingMachineItems.get(row).get(col);
            if (cashPaymentMachine.pay(selectedItem.getProductCurrency(), selectedItem.getProductPrice())) {
                // 우선 현금부터 먼저 결제한다.
                vendingMachineOutletItems.add(selectedItem);
                printWhenSelectItemCashSuccess();
            } else if (cardPaymentMachine.pay(selectedItem.getProductCurrency(), selectedItem.getProductPrice())) {
                // 그 다음 카드 결제를 시도한다.
                vendingMachineOutletItems.add(selectedItem);
                printWhenSelectItemCardSuccess();
            } else {
                printWhenAllPaymentFail();
            }
        } else {
            printWrongPositionItemSelected();
        }
    }

    public void pushCard(Card card) {
        if (cardPaymentMachine.receive(card)) {
            printWhenPushAvailableCard();
        } else {
            printWhenPushNotAvailableCard();
        }
    }

    public void change() {
        cashPaymentMachine.change();
        cardPaymentMachine.change();
        printWhenChange();
    }
}
