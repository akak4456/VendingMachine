package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.material.RealObject;

import java.util.List;

public interface ItemSelector <S extends SelectorDTO> {
    void showSelector(VendingMachine vendingMachine);

    void pushRealObjects(List<RealObject> realObjects);

    RealObject selectItem(S selectorDTO);
}
