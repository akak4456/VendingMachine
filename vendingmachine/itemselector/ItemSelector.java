package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ItemSelector<S extends SelectorDTO> {

    protected ArrayList<ArrayList<RealObject>> itemsInMachine;

    protected List<List<Item>> displayItem;

    abstract public void showSelector(VendingMachine<S> vendingMachine);

    abstract public void pushRealObjects(List<RealObject> realObjects);

    abstract public RealObject selectItem(S selectorDTO, VendingMachineVO paidVO);

    protected boolean hasRecipeItemInMachine(List<RealObject> recipes) {
        for (RealObject recipe : recipes) {
            for (int row = 0; row < itemsInMachine.size(); row++) {
                ArrayList<RealObject> tmp = itemsInMachine.get(row);
                if (!tmp.isEmpty() && tmp.get(0).getClass() == recipe.getClass()) {
                    int hasQuantity = 0;
                    for (RealObject t : tmp) {
                        hasQuantity += t.getQuantity();
                    }
                    if (recipe.getQuantity() > hasQuantity) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected RealObject subtractRealObjectAndMixIfNeeded(Item targetItem) {
        for (RealObject recipe : targetItem.getRequiredRealObjects()) {
            boolean isDone = false;
            for (ArrayList<RealObject> items : itemsInMachine) {
                if (!items.isEmpty() && items.get(0).getClass() == recipe.getClass()) {
                    for (RealObject item : items) {
                        if (item.getQuantity() >= recipe.getQuantity()) {
                            item.subtractQuantity(recipe.getQuantity());
                            if (item.getQuantity() == 0) {
                                items.remove(item); // 이거 나중에 문제가 될 것 같은데...
                            }
                            isDone = true;
                            break;
                        } else {
                            recipe.subtractQuantity(item.getQuantity());
                            items.remove(item);
                        }
                    }
                }
                if (isDone) {
                    break;
                }
            }
        }
        return targetItem.resultRealObject;
    }

    class Item {
        public static final String BUTTON_STATUS_OFF = "off";
        public static final String BUTTON_STATUS_ON = "on";
        public static final String BUTTON_STATUS_NO_ITEM = "재고없음";
        private final String itemDesc;
        private final List<RealObject> requiredRealObjects;
        private final RealObject resultRealObject;
        private final VendingMachineVO vo;
        private String buttonStatus;

        public Item(String itemDesc, List<RealObject> requiredObject, RealObject resultRealObject, VendingMachineVO vo) {
            this.itemDesc = itemDesc;
            this.requiredRealObjects = requiredObject;
            this.resultRealObject = resultRealObject;
            this.vo = vo;
            this.buttonStatus = BUTTON_STATUS_OFF;
        }

        public String getItemDesc() {
            return itemDesc;
        }

        public VendingMachineVO getVo() {
            return vo;
        }

        public List<RealObject> getRequiredRealObjects() {
            return requiredRealObjects;
        }

        public RealObject getResultRealObject() {
            return resultRealObject;
        }

        public String getButtonStatus() {
            return buttonStatus;
        }

        public void setButtonStatus(String buttonStatus) {
            this.buttonStatus = buttonStatus;
        }
    }
}
