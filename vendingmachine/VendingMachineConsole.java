package vendingmachine;

import vendingmachine.itemselector.ItemSelector;
import vendingmachine.itemselector.SelectorDTO;
import vendingmachine.paymentmachine.CashPaymentMachine;

public class VendingMachineConsole<S extends SelectorDTO> extends VendingMachine<S> {

    public VendingMachineConsole(CashPaymentMachine cashPaymentMachine, ItemSelector<S> itemSelector) {
        super(cashPaymentMachine, itemSelector);
    }

    @Override
    protected void printWhenPushPaperSuccess() {
        System.out.println("현금을 넣었습니다. (현재잔액:" + cashPaymentMachine.displayCurrentUserInputMoney() + ")");
    }

    @Override
    protected void printWhenPushPaperFail() {
        System.out.println("잘못된 종이를 넣었습니다.");
    }

    @Override
    protected void printWhenPushMetalSuccess() {
        System.out.println("동전을 넣었습니다. (현재잔액:" + cashPaymentMachine.displayCurrentUserInputMoney() + ")");
    }

    @Override
    protected void printWhenPushMetalFail() {
        System.out.println("잘못된 동전을 넣었습니다.");
    }

    @Override
    protected void printWhenIsAllChange() {
        System.out.println("잔돈을 성공적으로 반환하였습니다.");
    }

    @Override
    protected void printWhenIsNotAllChange() {
        System.out.println("문제가 생겨서 잔돈을 반환하지 못했습니다. 관리자에게 문의해주세요.");
    }
}
