package vendingmachine;

import vendingmachine.item.VendingMachineConsoleItem;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;

import java.util.List;

public class VendingMachineConsole extends VendingMachine<VendingMachineConsoleItem> {
    public VendingMachineConsole(CashPaymentMachine cashPaymentMachine, CardPaymentMachine cardPaymentMachine, List<List<VendingMachineConsoleItem>> vendingMachineItems) {
        super(cashPaymentMachine, cardPaymentMachine, vendingMachineItems);
    }

    @Override
    public void printDisplayItems() {
        System.out.println("-----------------------------------------------------------------------");
        for (List<VendingMachineConsoleItem> items : vendingMachineItems) {
            for (VendingMachineConsoleItem item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    @Override
    public void printOutletItems() {
        System.out.print("배출구에 있는 아이템들: ");
        for (VendingMachineConsoleItem item : vendingMachineOutletItems) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    @Override
    protected void printWhenPushAvailableCash() {
        System.out.println("자판기에 돈을 넣었습니다. (현재 잔액:" + cashPaymentMachine.getCurrentCashString() + ")");
        for (List<VendingMachineConsoleItem> items : vendingMachineItems) {
            for (VendingMachineConsoleItem item : items) {
                item.setButtonLightOn(cashPaymentMachine.isSourceGreaterThanOrEqualsTo(item.getProductCurrency(), item.getProductPrice()));
            }
        }
    }

    @Override
    protected void printWhenPushNotAvailableCash() {
        System.out.println("자판기에 지원하지 않는 돈을 넣으셨습니다.");
    }

    @Override
    protected void printWhenSelectItemCashSuccess() {
        System.out.println("현금 결제 하였습니다. (현재 잔액:" + cashPaymentMachine.getCurrentCashString() + ")");
        for (List<VendingMachineConsoleItem> items : vendingMachineItems) {
            for (VendingMachineConsoleItem item : items) {
                item.setButtonLightOn(cashPaymentMachine.isSourceGreaterThanOrEqualsTo(item.getProductCurrency(), item.getProductPrice()));
            }
        }
    }

    @Override
    protected void printWhenSelectItemCardSuccess() {
        System.out.println("카드 결제 하였습니다.");
    }

    @Override
    protected void printWhenAllPaymentFail() {
        System.out.println("어떤 결제 수단도 성공하지 못했습니다.");
    }

    @Override
    protected void printWrongPositionItemSelected() {
        System.out.println("잘못된 위치의 아이템을 골랐습니다.");
    }

    @Override
    protected void printWhenPushAvailableCard() {
        System.out.println("카드를 정상적으로 넣었습니다.");
        allButtonOnOrOff(true);
    }

    @Override
    protected void printWhenPushNotAvailableCard() {
        System.out.println("카드를 넣는 과정에서 문제가 생겼습니다.");
        allButtonOnOrOff(false);
    }

    @Override
    protected void printWhenChange() {
        System.out.println("돈 또는 카드를 반환하였습니다.");
        allButtonOnOrOff(false);
    }

    private void allButtonOnOrOff(Boolean isButtonOn) {
        for (List<VendingMachineConsoleItem> items : vendingMachineItems) {
            for (VendingMachineConsoleItem item : items) {
                item.setButtonLightOn(isButtonOn);
            }
        }
    }
}
