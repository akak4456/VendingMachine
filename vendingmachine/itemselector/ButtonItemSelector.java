package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;
import vendingmachine.material.discrete.*;

import java.util.ArrayList;
import java.util.List;

public class ButtonItemSelector extends ItemSelector<ButtonSelectorDTO> {

    public ButtonItemSelector() {
        displayItem = List.of(
                List.of(
                        new Item("사이다", List.of(new Cider()), new Cider(), new VendingMachineVO(2000)),
                        new Item("콜라", List.of(new Coke()), new Coke(), new VendingMachineVO(2000)),
                        new Item("과일", List.of(new Fruit()), new Fruit(), new VendingMachineVO(3000)),
                        new Item("금", List.of(new Gold()), new Gold(), new VendingMachineVO(10000))
                ),
                List.of(
                        new Item("껌", List.of(new Gum()), new Gum(), new VendingMachineVO(500)),
                        new Item("아이스크림", List.of(new IceCream()), new IceCream(), new VendingMachineVO(4000)),
                        new Item("과자", List.of(new Snack()), new Snack(), new VendingMachineVO(4000)),
                        new Item("물병", List.of(new WaterBottle()), new WaterBottle(), new VendingMachineVO(1000))
                )
        );
        itemsInMachine = new ArrayList<>();
    }

    @Override
    public void showSelector(VendingMachine<ButtonSelectorDTO> vendingMachine) {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < displayItem.size(); i++) {
            for (int j = 0; j < displayItem.get(i).size(); j++) {
                Item item = displayItem.get(i).get(j);
                item.setButtonStatus(Item.BUTTON_STATUS_OFF);

                if (hasRecipeItemInMachine(item.getRequiredRealObjects())) {
                    if (false) {
                        // TODO 카드결제가 이루어지고 있다면
                        item.setButtonStatus(Item.BUTTON_STATUS_ON);
                    } else if (vendingMachine.isCashPaymentMachineVOGreaterThanOrEqualsTo(item.getVo())) {
                        item.setButtonStatus(Item.BUTTON_STATUS_ON);
                    }
                } else {
                    item.setButtonStatus(Item.BUTTON_STATUS_NO_ITEM);
                }
                System.out.println(item.getItemDesc() + "(" + item.getButtonStatus() + ")");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void pushRealObjects(List<RealObject> realObjects) {

        for (RealObject realObject : realObjects) {
            boolean isFound = false;
            for (ArrayList<RealObject> tmp : itemsInMachine) {
                if (!tmp.isEmpty() && tmp.get(0).getClass() == realObject.getClass()) {
                    isFound = true;
                    tmp.add(realObject);
                    break;
                }
            }
            if (!isFound) {
                ArrayList<RealObject> tmp = new ArrayList<>();
                tmp.add(realObject);
                itemsInMachine.add(tmp);
            }
        }
    }

    @Override
    public RealObject selectItem(ButtonSelectorDTO selectorDTO, VendingMachineVO paidVO) {
        if (0 <= selectorDTO.getRow() && selectorDTO.getRow() < displayItem.size()) {
            if (0 <= selectorDTO.getCol() && selectorDTO.getCol() < displayItem.get(selectorDTO.getRow()).size()) {
                Item item = displayItem.get(selectorDTO.getRow()).get(selectorDTO.getCol());
                if (hasRecipeItemInMachine(item.getRequiredRealObjects())) {
                    if (paidVO.isGreaterThanOrEqualsTo(item.getVo())) {
                        return subtractRealObjectAndMixIfNeeded(item);
                    }
                }
            }
        }
        return null;
    }
}
