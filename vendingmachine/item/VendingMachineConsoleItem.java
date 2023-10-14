package vendingmachine.item;

import vendingmachine.Currency;

public class VendingMachineConsoleItem extends VendingMachineItem {
    private String productName;
    private boolean isButtonLightOn;

    public VendingMachineConsoleItem(String productName, Currency productCurrency, int productPrice) {
        super(productCurrency, productPrice);
        this.productName = productName;
        isButtonLightOn = false;
    }

    @Override
    public String toString() {
        return productName + " (" + productPrice + productCurrency.getUnit() + ", " + (isButtonLightOn ? "on" : "off") + ")";
    }

    public void setButtonLightOn(boolean buttonLightOn) {
        isButtonLightOn = buttonLightOn;
    }
}
