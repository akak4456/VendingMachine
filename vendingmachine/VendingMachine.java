package vendingmachine;

import vendingmachine.moneysource.card.Card;
import vendingmachine.moneysource.cash.Cash;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

    private CashPaymentMachine cashPaymentMachine;
    private CardPaymentMachine cardPaymentMachine;

    private List<List<Item>> vendingMachineItems;

    private List<Item> vendingMachineOutletItems;

    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            CardPaymentMachine cardPaymentMachine,
            List<List<Item>> vendingMachineItems
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.cardPaymentMachine = cardPaymentMachine;
        this.vendingMachineItems = vendingMachineItems;
        this.vendingMachineOutletItems = new ArrayList<>();
    }

    public void printDisplayItems() {
        System.out.println("-----------------------------------------------------------------------");
        for (List<Item> items : vendingMachineItems) {
            for (Item item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    public void pushCash(Cash cash) {
        if (cashPaymentMachine.receive(cash)) {
            System.out.println("자판기에 돈을 넣었습니다. (현재 잔액:" + cashPaymentMachine.getTotalMoney() + ")");
            for (List<Item> items : vendingMachineItems) {
                for (Item item : items) {
                    item.isButtonLightOn = cash.isGreaterThanOrEqualsTo(item.productCurrency, item.productPrice);
                }
            }
        } else {
            System.out.println("자판기에 지원하지 않는 돈을 넣으셨습니다.");
        }
    }

    public void selectItem(int row, int col) {
        if (0 <= row && row < vendingMachineItems.size() && 0 <= col && col < vendingMachineItems.get(row).size()) {
            Item selectedItem = vendingMachineItems.get(row).get(col);
            if (selectedItem.isButtonLightOn) {
                if (cashPaymentMachine.pay(selectedItem.productCurrency, selectedItem.productPrice)) {
                    // 우선 현금부터 먼저 결제한다.
                    System.out.println("현금 결제 하였습니다. (현재 잔액:" + cashPaymentMachine.getTotalMoney() + ")");
                    allButtonOff();
                    vendingMachineOutletItems.add(selectedItem);
                } else if (cardPaymentMachine.pay(selectedItem.productCurrency, selectedItem.productPrice)) {
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
            for (List<Item> items : vendingMachineItems) {
                for (Item item : items) {
                    item.isButtonLightOn = true;
                }
            }
        } else {
            System.out.println("카드를 넣는 과정에서 문제가 생겼습니다.");
        }
    }

    public void printOutletItems() {
        System.out.print("배출구에 있는 아이템들: ");
        for (Item item : vendingMachineOutletItems) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private void allButtonOff() {
        for (List<Item> items : vendingMachineItems) {
            for (Item item : items) {
                item.isButtonLightOn = false;
            }
        }
    }

    public static class Item {
        private String productName;
        private Currency productCurrency;
        private int productPrice;
        private boolean isButtonLightOn;

        public Item(String productName, Currency productCurrency, int productPrice) {
            this.productName = productName;
            this.productCurrency = productCurrency;
            this.productPrice = productPrice;
            isButtonLightOn = false;
        }

        @Override
        public String toString() {
            return productName + " (" + productPrice + productCurrency.unit + ", " + (isButtonLightOn ? "on" : "off") + ")";
        }
    }
}
