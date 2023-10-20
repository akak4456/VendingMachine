package vendingmachine.itemselector;

import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;

import java.util.List;

public class NumpadSelectorItem extends Item {
    private final String number;

    public NumpadSelectorItem(String itemDesc, List<RealObject> requiredObject, RealObject resultRealObject, VendingMachineVO vo, String number) {
        super(itemDesc, requiredObject, resultRealObject, vo);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
