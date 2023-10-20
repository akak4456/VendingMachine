package vendingmachine.itemselector;

import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;

import java.util.List;

public abstract class Item {
    private final String itemDesc;
    private final List<RealObject> requiredRealObjects;
    private final RealObject resultRealObject;
    private final VendingMachineVO vo;

    public Item(String itemDesc, List<RealObject> requiredObject, RealObject resultRealObject, VendingMachineVO vo) {
        this.itemDesc = itemDesc;
        this.requiredRealObjects = requiredObject;
        this.resultRealObject = resultRealObject;
        this.vo = vo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public List<RealObject> getRequiredRealObjects() {
        return requiredRealObjects;
    }

    public RealObject getResultRealObject() {
        return resultRealObject;
    }

    public VendingMachineVO getVo() {
        return vo;
    }
}
