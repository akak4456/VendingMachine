package vendingmachine.item;

import vendingmachine.Currency;

public abstract class VendingMachineItem {
    protected Currency productCurrency;
    protected int productPrice;

    public VendingMachineItem(Currency productCurrency, int productPrice) {
        this.productCurrency = productCurrency;
        this.productPrice = productPrice;
    }

    public Currency getProductCurrency(){
        return productCurrency;
    }

    public int getProductPrice() {
        return productPrice;
    }
}
