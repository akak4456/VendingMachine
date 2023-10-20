package vendingmachine;

import vendingmachine.itemselector.ButtonSelectorDTO;
import vendingmachine.itemselector.Item;
import vendingmachine.itemselector.ItemSelector;
import vendingmachine.itemselector.SelectorDTO;
import vendingmachine.material.RealObject;
import vendingmachine.material.continuous.ContinuousRealObject;
import vendingmachine.material.discrete.DiscreteRealObject;
import vendingmachine.material.discrete.Metal;
import vendingmachine.material.discrete.Paper;
import vendingmachine.paymentmachine.CashPaymentMachine;
import vendingmachine.people.Manager;
import vendingmachine.people.NormalPeople;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class VendingMachine<S extends SelectorDTO, I extends Item> {
    private final CashPaymentMachine cashPaymentMachine;

    private final ItemSelector<S, I> itemSelector;

    private final ArrayList<RealObject> outlet = new ArrayList<>();


    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            ItemSelector<S, I> itemSelector
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.itemSelector = itemSelector;
    }

    public void showSelector() {
        itemSelector.showSelector(this);
    }

    public void showOutlet() {
        System.out.print("배출구에 있는 아이템: ");
        for (RealObject object : outlet) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public void pushPaper(NormalPeople normalPeople, Paper paper) {
        if (normalPeople.containsPaper(paper) && cashPaymentMachine.receivePaper(paper)) {
            normalPeople.removePaper(paper);
            System.out.println("현금을 넣었습니다. (현재잔액:" + cashPaymentMachine.displayCurrentUserInputMoney() + ")");
        } else {
            System.out.println("잘못된 종이를 넣었습니다.");
        }
        showSelector();
    }

    public void pushMetal(NormalPeople normalPeople, Metal metal) {
        if (normalPeople.containsMetal(metal) && cashPaymentMachine.receiveMetal(metal)) {
            normalPeople.removeMetal(metal);
            System.out.println("동전을 넣었습니다. (현재잔액:" + cashPaymentMachine.displayCurrentUserInputMoney() + ")");
        } else {
            System.out.println("잘못된 동전을 넣었습니다.");
        }
        showSelector();
    }

    public void change(NormalPeople normalPeople) {
        normalPeople.addAllPaper(cashPaymentMachine.changeByCash());
        normalPeople.addAllMetal(cashPaymentMachine.changeByCoin());
        if (cashPaymentMachine.isAllChange()) {
            System.out.println("잔돈을 성공적으로 반환하였습니다.");
        } else {
            System.out.println("문제가 생겨서 잔돈을 반환하지 못했습니다. 관리자에게 문의해주세요.");
        }
        showSelector();
    }

    public void pushRealObject(Manager manager, Map<Class<? extends RealObject>, Integer> objectMap) {
        ArrayList<RealObject> objects = new ArrayList<>();
        for (Map.Entry<Class<? extends RealObject>, Integer> entry : objectMap.entrySet()) {
            Class<? extends RealObject> clazz = entry.getKey();
            try {
                if (clazz.isAssignableFrom(DiscreteRealObject.class)) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        DiscreteRealObject object = (DiscreteRealObject) clazz.getConstructor().newInstance();
                        objects.add(object);
                    }
                } else {
                    ContinuousRealObject object = (ContinuousRealObject) clazz.getConstructor(Integer.class).newInstance(entry.getValue());
                    objects.add(object);
                }
            } catch (Exception e) {

            }

        }
        itemSelector.pushRealObjects(objects);
    }

    public boolean isCashPaymentMachineVOGreaterThanOrEqualsTo(VendingMachineVO vo) {
        return cashPaymentMachine.isUserVOGreaterThanOrEqualsTo(vo);
    }

    public void selectItem(S selectorDTO) {
        // 우선 현금 결제부터 시도한다
        Pair<RealObject, VendingMachineVO> pair = itemSelector.selectItem(selectorDTO, cashPaymentMachine.getUserInputVO());
        if (pair != null) {
            cashPaymentMachine.pay(pair.getY());
            outlet.add(pair.getX());
            return;
        }
        // 그 다음 카드 결제를 시도한다.
    }
}
