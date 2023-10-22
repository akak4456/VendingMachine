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
import vendingmachine.material.discrete.Plastic;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;
import vendingmachine.people.Manager;
import vendingmachine.people.NormalPeople;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class VendingMachine<S extends SelectorDTO, I extends Item> {
    private final CashPaymentMachine cashPaymentMachine;
    private final CardPaymentMachine cardPaymentMachine;

    private final ItemSelector<S, I> itemSelector;

    private final ArrayList<RealObject> outlet = new ArrayList<>();


    public VendingMachine(
            CashPaymentMachine cashPaymentMachine,
            CardPaymentMachine cardPaymentMachine,
            ItemSelector<S, I> itemSelector
    ) {
        this.cashPaymentMachine = cashPaymentMachine;
        this.cardPaymentMachine = cardPaymentMachine;
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

    public void pushPlastic(NormalPeople normalPeople, Plastic plastic) {
        if (normalPeople.containsPlastic(plastic) && cardPaymentMachine.receivePlastic(plastic)) {
            System.out.println("카드를 넣었습니다.");
        } else {
            System.out.println("잘못된 카드를 넣었습니다.");
        }
        showSelector();
    }

    public void pushRealObject(Manager manager, Map<Class<? extends RealObject>, Integer> objectMap) {
        ArrayList<RealObject> objects = new ArrayList<>();
        for (Map.Entry<Class<? extends RealObject>, Integer> entry : objectMap.entrySet()) {
            Class<? extends RealObject> clazz = entry.getKey();
            try {
                if (DiscreteRealObject.class.isAssignableFrom(clazz)) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        DiscreteRealObject object = (DiscreteRealObject) clazz.getConstructor().newInstance();
                        objects.add(object);
                    }
                } else {
                    ContinuousRealObject object = (ContinuousRealObject) clazz.getConstructor(Integer.class).newInstance(entry.getValue());
                    objects.add(object);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        itemSelector.pushRealObjects(objects);
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

    public boolean isCashPaymentMachineVOGreaterThanOrEqualsTo(VendingMachineVO vo) {
        return cashPaymentMachine.isUserVOGreaterThanOrEqualsTo(vo);
    }

    public boolean isCardPaymentAvailable() {
        return cardPaymentMachine.isCardAvailable();
    }

    public void selectItem(S selectorDTO) {
        // 우선 현금 결제부터 시도한다
        Pair<RealObject, VendingMachineVO> pair = itemSelector.selectItem(selectorDTO, cashPaymentMachine.getUserInputVO());
        if (pair != null) {
            if (cashPaymentMachine.pay(pair.getY())) {
                outlet.add(pair.getX());
                System.out.println("결제가 정상적으로 이루어졌습니다.");
            } else {
                System.out.println("결제에 문제가 생겼습니다.");
            }
            return;
        }
        // 그 다음 카드 결제를 시도한다.
        pair = itemSelector.selectItem(selectorDTO, VendingMachineVO.MAX_VO);
        if (pair != null) {
            if (cardPaymentMachine.pay(pair.getY())) {
                outlet.add(pair.getX());
                System.out.println("결제가 정상적으로 이루어졌습니다.");
            } else {
                System.out.println("결제에 문제가 생겼습니다.");
            }
            return;
        }
        System.out.println("아이템을 선택하는 과정 중에 문제가 발생했습니다. 관리자에게 문의해주세요.");
    }
}
