package vendingmachine.itemselector;

public class NumpadSelectorDTO extends SelectorDTO{
    public final String number;

    public NumpadSelectorDTO(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
