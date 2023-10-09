package vendingmachine;

import vendingmachine.currency.Currency;
import vendingmachine.currency.NotSameCurrencyKindException;
import vendingmachine.moneysource.card.Card;
import vendingmachine.moneysource.cash.Cash;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;
import vendingmachine.paymentmachine.PaymentMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine<C extends Currency> {

    private CashPaymentMachine cashPaymentMachine;
    private CardPaymentMachine cardPaymentMachine;

    private List<List<Item<C>>> vendingMachineItems;

    private List<Item<C>> vendingMachineOutletItems;

    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            CardPaymentMachine cardPaymentMachine,
            List<List<Item<C>>> vendingMachineItems
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.cardPaymentMachine = cardPaymentMachine;
        this.vendingMachineItems = vendingMachineItems;
        this.vendingMachineOutletItems = new ArrayList<>();
    }

    public void printDisplayItems() {
        System.out.println("-----------------------------------------------------------------------");
        for (List<Item<C>> items : vendingMachineItems) {
            for (Item<C> item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void pushCash(Cash cash) {
        try {
            if (cashPaymentMachine.receive(cash)) {
                System.out.println("자판기에 돈을 넣었습니다. (현재 잔액:" + cashPaymentMachine.getTotalCurrency() + ")");
                for (List<Item<C>> items : vendingMachineItems) {
                    for (Item<C> item : items) {
                        item.isButtonLightOn = cashPaymentMachine.getTotalCurrency().isGreaterThanOrEqualsTo(item.productPrice);
                    }
                }
            } else {
                System.out.println("자판기에 지원하지 않는 돈을 넣으셨습니다.");
            }
        } catch (NotSameCurrencyKindException e) {
            System.out.println("자판기에 지원하지 않는 돈을 넣으셨습니다.");
        }
    }

    public void selectItem(int row, int col) {
        if (0 <= row && row < vendingMachineItems.size() && 0 <= col && col < vendingMachineItems.get(row).size()) {
            Item<C> selectedItem = vendingMachineItems.get(row).get(col);
            if (selectedItem.isButtonLightOn) {
                if (cashPaymentMachine.pay(selectedItem.productPrice)) {
                    // 우선 현금부터 먼저 결제한다.
                    System.out.println("현금 결제 하였습니다. (현재 잔액:" + cashPaymentMachine.getTotalCurrency() + ")");
                    allButtonOff();
                    vendingMachineOutletItems.add(selectedItem);
                } else if (cardPaymentMachine.pay(selectedItem.productPrice)) {
                    // 그 다음 카드 결제를 시도한다.
                    System.out.println("카드 결제 하였습니다.");
                    allButtonOff();
                    vendingMachineOutletItems.add(selectedItem);
                } else {
                    System.out.println("어떤 결제 수단도 성공하지 못했습니다.");
                }
            } else {
                System.out.println("버튼이 켜져 있지 않는 아이템을 골랐습니다.");
            }
        } else {
            System.out.println("잘못된 위치의 아이템을 골랐습니다.");
        }
    }

    public void pushCard(Card card) {
        if (cardPaymentMachine.receive(card)) {
            System.out.println("카드를 정상적으로 넣었습니다.");
            for (List<Item<C>> items : vendingMachineItems) {
                for (Item<C> item : items) {
                    item.isButtonLightOn = true;
                }
            }
        } else {
            System.out.println("카드를 넣는 과정에서 문제가 생겼습니다.");
        }
    }

    public void printOutletItems() {
        System.out.print("배출구에 있는 아이템들: ");
        for (Item<C> item : vendingMachineOutletItems) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private void allButtonOff() {
        for (List<Item<C>> items : vendingMachineItems) {
            for (Item<C> item : items) {
                item.isButtonLightOn = false;
            }
        }
    }

    public static class Item<C extends Currency> {
        private String productName;
        private C productPrice;
        private boolean isButtonLightOn;

        public Item(String productName, C productPrice) {
            this.productName = productName;
            this.productPrice = productPrice;
            isButtonLightOn = false;
        }

        @Override
        public String toString() {
            return productName + " (" + productPrice + ", " + (isButtonLightOn ? "on" : "off") + ")";
        }
    }
}
