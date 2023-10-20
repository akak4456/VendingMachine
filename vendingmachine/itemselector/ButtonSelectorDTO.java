package vendingmachine.itemselector;

public class ButtonSelectorDTO extends SelectorDTO{
    private final int row;
    private final int col;

    public ButtonSelectorDTO(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
