package vendingmachine.itemselector;

import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.discrete.*;

import java.util.List;

public class NumpadSelector extends ItemSelector<NumpadSelectorDTO, NumpadSelectorItem> {

    public NumpadSelector() {
        displayItem = List.of(
                List.of(
                        new NumpadSelectorItem("사이다", List.of(new Cider()), new Cider(), new VendingMachineVO(2000), "1"),
                        new NumpadSelectorItem("콜라", List.of(new Coke()), new Coke(), new VendingMachineVO(2000), "2"),
                        new NumpadSelectorItem("과일", List.of(new Fruit()), new Fruit(), new VendingMachineVO(3000), "3"),
                        new NumpadSelectorItem("금", List.of(new Gold()), new Gold(), new VendingMachineVO(10000), "4")
                ),
                List.of(
                        new NumpadSelectorItem("껌", List.of(new Gum()), new Gum(), new VendingMachineVO(500), "5"),
                        new NumpadSelectorItem("아이스크림", List.of(new IceCream()), new IceCream(), new VendingMachineVO(4000), "6"),
                        new NumpadSelectorItem("과자", List.of(new Snack()), new Snack(), new VendingMachineVO(4000), "7"),
                        new NumpadSelectorItem("물병", List.of(new WaterBottle()), new WaterBottle(), new VendingMachineVO(1000), "8")
                )
        );
    }

    @Override
    public void showSelector(VendingMachine<NumpadSelectorDTO, NumpadSelectorItem> vendingMachine) {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < displayItem.size(); i++) {
            for (int j = 0; j < displayItem.get(i).size(); j++) {
                NumpadSelectorItem item = displayItem.get(i).get(j);
                System.out.print(item.getItemDesc() + "(" + item.getNumber() + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
    }

    @Override
    protected Item selectItemLogic(NumpadSelectorDTO selectDTO) {
        for(List<NumpadSelectorItem> list: displayItem) {
            for(NumpadSelectorItem item : list) {
                if(item.getNumber().equals(selectDTO.getNumber())) {
                    return item;
                }
            }
        }
        return null;
    }
}
