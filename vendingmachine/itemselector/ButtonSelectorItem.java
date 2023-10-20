package vendingmachine.itemselector;

import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;

import java.util.List;

public class ButtonSelectorItem extends Item {
    public static final String BUTTON_STATUS_OFF = "off";
    public static final String BUTTON_STATUS_ON = "on";
    public static final String BUTTON_STATUS_NO_ITEM = "재고없음";

    private String buttonStatus;

    public ButtonSelectorItem(String itemDesc, List<RealObject> requiredObject, RealObject resultRealObject, VendingMachineVO vo) {
        super(itemDesc, requiredObject, resultRealObject, vo);
        buttonStatus = BUTTON_STATUS_OFF;
    }

    public String getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(String buttonStatus) {
        this.buttonStatus = buttonStatus;
    }
}
