package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;
import vendingmachine.material.finished.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ButtonItemSelector implements ItemSelector<ButtonSelectorDTO> {
    List<List<Item>> itemDeploy;
    ArrayList<ArrayList<Queue<Item>>> items;

    public ButtonItemSelector() {
        itemDeploy = List.of(
                List.of(
                        new Item(new Cider(), new VendingMachineVO(2000)),
                        new Item(new Coke(), new VendingMachineVO(2000)),
                        new Item(new Fruit(), new VendingMachineVO(3000)),
                        new Item(new Gold(), new VendingMachineVO(10000))
                ),
                List.of(
                        new Item(new Gum(), new VendingMachineVO(500)),
                        new Item(new IceCream(), new VendingMachineVO(4000)),
                        new Item(new Snack(), new VendingMachineVO(4000)),
                        new Item(new Water(), new VendingMachineVO(1000))
                )
        );
    }

    @Override
    public void showSelector(VendingMachine vendingMachine) {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < itemDeploy.size(); i++) {
            for (int j = 0; j < itemDeploy.get(i).size(); j++) {
                Item item = itemDeploy.get(i).get(j);
                item.setButtonOn(false);
                if (false) {
                    // TODO 카드결제가 이루어지고 있다면
                    item.setButtonOn(true);
                } else if (vendingMachine.isCashPaymentMachineVOGreaterThanOrEqualsTo(item.getVo())) {
                    if (items.get(i).get(j).size() > 0) {
                        item.setButtonOn(true);
                    }
                }

                System.out.print(item.realObject + "(" + item.isButtonOn + " 재고:" + items.get(i).get(j).size() + ")");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void pushRealObjects(List<RealObject> realObjects) {
        for (int i = 0; i < itemDeploy.size(); i++) {
            items.add(new ArrayList<>());
            for (int j = 0; j < itemDeploy.get(i).size(); j++) {
                items.get(i).add(new LinkedList<>());
            }
        }
        for (RealObject realObject : realObjects) {
            for (int i = 0; i < itemDeploy.size(); i++) {
                boolean isFound = false;
                for (int j = 0; j < itemDeploy.get(i).size(); j++) {
                    if (realObject.getClass() == itemDeploy.get(i).get(j).getRealObject().getClass()) {
                        items.get(i).get(j).add(itemDeploy.get(i).get(j));
                        isFound = true;
                        break;
                    }
                }
                if (isFound) {
                    break;
                }
            }
        }
    }

    @Override
    public RealObject selectItem(ButtonSelectorDTO selectorDTO) {
        if (0 <= selectorDTO.getRow() && selectorDTO.getRow() < itemDeploy.size()) {
            if (0 <= selectorDTO.getCol() && selectorDTO.getCol() < itemDeploy.get(selectorDTO.getRow()).size()) {
                if (items.get(selectorDTO.getRow()).get(selectorDTO.getCol()).size() > 0) {
                    return items.get(selectorDTO.getRow()).get(selectorDTO.getCol()).remove().realObject;
                }
            }
        }
        return null;
    }

    class Item {
        private RealObject realObject;
        private VendingMachineVO vo;

        private boolean isButtonOn;

        public Item(RealObject realObject, VendingMachineVO vo) {
            this.realObject = realObject;
            this.vo = vo;
            this.isButtonOn = false;
        }

        public RealObject getRealObject() {
            return realObject;
        }

        public VendingMachineVO getVo() {
            return vo;
        }

        public boolean isButtonOn() {
            return isButtonOn;
        }

        public void setButtonOn(boolean buttonOn) {
            isButtonOn = buttonOn;
        }
    }
}
