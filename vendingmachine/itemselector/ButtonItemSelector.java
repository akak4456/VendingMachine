package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;
import vendingmachine.material.discrete.*;

import java.util.ArrayList;
import java.util.List;

public class ButtonItemSelector extends ItemSelector<ButtonSelectorDTO, ButtonSelectorItem> {

    public ButtonItemSelector() {
        displayItem = List.of(
                List.of(
                        new ButtonSelectorItem("사이다", List.of(new Cider()), new Cider(), new VendingMachineVO(2000)),
                        new ButtonSelectorItem("콜라", List.of(new Coke()), new Coke(), new VendingMachineVO(2000)),
                        new ButtonSelectorItem("과일", List.of(new Fruit()), new Fruit(), new VendingMachineVO(3000)),
                        new ButtonSelectorItem("금", List.of(new Gold()), new Gold(), new VendingMachineVO(10000))
                ),
                List.of(
                        new ButtonSelectorItem("껌", List.of(new Gum()), new Gum(), new VendingMachineVO(500)),
                        new ButtonSelectorItem("아이스크림", List.of(new IceCream()), new IceCream(), new VendingMachineVO(4000)),
                        new ButtonSelectorItem("과자", List.of(new Snack()), new Snack(), new VendingMachineVO(4000)),
                        new ButtonSelectorItem("물병", List.of(new WaterBottle()), new WaterBottle(), new VendingMachineVO(1000))
                )
        );
    }

    @Override
    public void showSelector(VendingMachine<ButtonSelectorDTO, ButtonSelectorItem> vendingMachine) {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < displayItem.size(); i++) {
            for (int j = 0; j < displayItem.get(i).size(); j++) {
                ButtonSelectorItem item = displayItem.get(i).get(j);
                item.setButtonStatus(ButtonSelectorItem.BUTTON_STATUS_OFF);

                if (hasRecipeItemInMachine(item.getRequiredRealObjects())) {
                    if (false) {
                        // TODO 카드결제가 이루어지고 있다면
                        item.setButtonStatus(ButtonSelectorItem.BUTTON_STATUS_ON);
                    } else if (vendingMachine.isCashPaymentMachineVOGreaterThanOrEqualsTo(item.getVo())) {
                        item.setButtonStatus(ButtonSelectorItem.BUTTON_STATUS_ON);
                    }
                } else {
                    item.setButtonStatus(ButtonSelectorItem.BUTTON_STATUS_NO_ITEM);
                }
                System.out.print(item.getItemDesc() + "(" + item.getButtonStatus() + ") ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    protected Item selectItemLogic(ButtonSelectorDTO selectDTO) {
        if(0 <= selectDTO.getRow() && selectDTO.getRow() < displayItem.size()) {
            if(0 <= selectDTO.getCol() && selectDTO.getCol() < displayItem.get(selectDTO.getRow()).size()) {
                return displayItem.get(selectDTO.getRow()).get(selectDTO.getCol());
            }
        }
        return null;
    }

}
