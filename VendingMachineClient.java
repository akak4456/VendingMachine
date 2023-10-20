import vendingmachine.VendingMachine;
import vendingmachine.itemselector.ButtonItemSelector;
import vendingmachine.itemselector.ButtonSelectorDTO;
import vendingmachine.itemselector.ButtonSelectorItem;
import vendingmachine.itemselector.Item;
import vendingmachine.material.discrete.*;
import vendingmachine.paymentmachine.CashPaymentMachine;
import vendingmachine.people.Manager;
import vendingmachine.people.NormalPeople;
import vendingmachine.won.WonCash;
import vendingmachine.won.WonCoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class VendingMachineClient {
    public static void main(String[] args) {
        VendingMachineClient client = new VendingMachineClient();
        client.story1();
    }

    public void story1() {
        NormalPeople normalPeople = generateNormalPeople1();
        Manager manager = new Manager(new ArrayList<>(), new ArrayList<>());
        VendingMachine<ButtonSelectorDTO, ButtonSelectorItem> vendingMachine = new VendingMachine<>(
                generateCashPaymentMachine(10, 10, 10, 10, 10, 10, 10, 10),
                new ButtonItemSelector()
        );
        vendingMachine.pushRealObject(
                manager,
                Map.of(
                        Coke.class, 10,
                        Cider.class, 10,
                        Fruit.class, 10,
                        Gold.class, 10,
                        Gum.class, 10,
                        IceCream.class, 10,
                        Snack.class, 10,
                        WaterBottle.class, 10
                )
        );
        vendingMachine.showSelector();
        vendingMachine.pushPaper(normalPeople, normalPeople.getPaperAt(0));
        vendingMachine.pushPaper(normalPeople, normalPeople.getPaperAt(1));
        vendingMachine.selectItem(new ButtonSelectorDTO(1, 0));
        vendingMachine.showOutlet();
        vendingMachine.showSelector();
    }

    private NormalPeople generateNormalPeople1() {
        ArrayList<Paper> normalPeoplePaper = new ArrayList<>();
        normalPeoplePaper.add(new Paper("아무것도 아님", "일반종이", 0, "", false));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_1000.getCharacterDesign(), WonCash.WON_CASH_1000.getPaperKind(), WonCash.WON_CASH_1000.getDisplayValue(), WonCash.WON_CASH_1000.getHolographic(), false));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_1000.getCharacterDesign(), WonCash.WON_CASH_1000.getPaperKind(), WonCash.WON_CASH_1000.getDisplayValue(), WonCash.WON_CASH_1000.getHolographic(), false));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_1000.getCharacterDesign(), WonCash.WON_CASH_1000.getPaperKind(), WonCash.WON_CASH_1000.getDisplayValue(), WonCash.WON_CASH_1000.getHolographic(), false));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_5000.getCharacterDesign(), WonCash.WON_CASH_5000.getPaperKind(), WonCash.WON_CASH_5000.getDisplayValue(), WonCash.WON_CASH_5000.getHolographic(), false));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_5000.getCharacterDesign(), WonCash.WON_CASH_5000.getPaperKind(), WonCash.WON_CASH_5000.getDisplayValue(), WonCash.WON_CASH_5000.getHolographic(), true));
        normalPeoplePaper.add(new Paper(WonCash.WON_CASH_10000.getCharacterDesign(), WonCash.WON_CASH_10000.getPaperKind(), WonCash.WON_CASH_10000.getDisplayValue(), "위조지폐", false));
        normalPeoplePaper.add(new Paper("신샤임당", WonCash.WON_CASH_50000.getPaperKind(), WonCash.WON_CASH_50000.getDisplayValue(), WonCash.WON_CASH_5000.getHolographic(), false));
        ArrayList<Metal> normalPeopleMetal = new ArrayList<>();
        normalPeopleMetal.add(new Metal("별", 0, 0, "별", "철"));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_10.getShape(), WonCoin.WON_COIN_10.getTeethCount(), WonCoin.WON_COIN_10.getDisplayValue(), WonCoin.WON_COIN_10.getDesign(), WonCoin.WON_COIN_10.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_10.getShape(), WonCoin.WON_COIN_10.getTeethCount(), WonCoin.WON_COIN_10.getDisplayValue(), WonCoin.WON_COIN_10.getDesign(), WonCoin.WON_COIN_10.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_10.getShape(), WonCoin.WON_COIN_10.getTeethCount(), WonCoin.WON_COIN_10.getDisplayValue(), WonCoin.WON_COIN_10.getDesign(), WonCoin.WON_COIN_10.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_50.getShape(), WonCoin.WON_COIN_50.getTeethCount(), WonCoin.WON_COIN_50.getDisplayValue(), WonCoin.WON_COIN_50.getDesign(), WonCoin.WON_COIN_50.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_50.getShape(), WonCoin.WON_COIN_50.getTeethCount(), WonCoin.WON_COIN_50.getDisplayValue(), WonCoin.WON_COIN_50.getDesign(), WonCoin.WON_COIN_50.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_50.getShape(), WonCoin.WON_COIN_50.getTeethCount(), WonCoin.WON_COIN_50.getDisplayValue(), WonCoin.WON_COIN_50.getDesign(), WonCoin.WON_COIN_50.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_50.getShape(), WonCoin.WON_COIN_50.getTeethCount(), WonCoin.WON_COIN_50.getDisplayValue(), WonCoin.WON_COIN_50.getDesign(), WonCoin.WON_COIN_50.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_100.getShape(), WonCoin.WON_COIN_100.getTeethCount(), WonCoin.WON_COIN_100.getDisplayValue(), WonCoin.WON_COIN_100.getDesign(), WonCoin.WON_COIN_100.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_100.getShape(), WonCoin.WON_COIN_100.getTeethCount(), WonCoin.WON_COIN_100.getDisplayValue(), WonCoin.WON_COIN_100.getDesign(), WonCoin.WON_COIN_100.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_100.getShape(), 100, WonCoin.WON_COIN_100.getDisplayValue(), WonCoin.WON_COIN_100.getDesign(), WonCoin.WON_COIN_100.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), WonCoin.WON_COIN_500.getDesign(), WonCoin.WON_COIN_500.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), WonCoin.WON_COIN_500.getDesign(), WonCoin.WON_COIN_500.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), WonCoin.WON_COIN_500.getDesign(), WonCoin.WON_COIN_500.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), WonCoin.WON_COIN_500.getDesign(), WonCoin.WON_COIN_500.getMetalKind()));
        normalPeopleMetal.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), "햑", WonCoin.WON_COIN_500.getMetalKind()));
        return new NormalPeople(
                normalPeoplePaper,
                normalPeopleMetal
        );
    }

    private CashPaymentMachine generateCashPaymentMachine(int won10Count, int won50Count, int won100Count, int won500Count, int won1000Count, int won5000Count, int won10000Count, int won50000Count) {
        ArrayList<Metal> metals = new ArrayList<>();
        for (int i = 0; i < won10Count; i++) {
            metals.add(new Metal(WonCoin.WON_COIN_10.getShape(), WonCoin.WON_COIN_10.getTeethCount(), WonCoin.WON_COIN_10.getDisplayValue(), WonCoin.WON_COIN_10.getDesign(), WonCoin.WON_COIN_10.getMetalKind()));
        }
        for (int i = 0; i < won50Count; i++) {
            metals.add(new Metal(WonCoin.WON_COIN_50.getShape(), WonCoin.WON_COIN_50.getTeethCount(), WonCoin.WON_COIN_50.getDisplayValue(), WonCoin.WON_COIN_50.getDesign(), WonCoin.WON_COIN_50.getMetalKind()));
        }
        for (int i = 0; i < won100Count; i++) {
            metals.add(new Metal(WonCoin.WON_COIN_100.getShape(), WonCoin.WON_COIN_100.getTeethCount(), WonCoin.WON_COIN_100.getDisplayValue(), WonCoin.WON_COIN_100.getDesign(), WonCoin.WON_COIN_100.getMetalKind()));
        }
        for (int i = 0; i < won500Count; i++) {
            metals.add(new Metal(WonCoin.WON_COIN_500.getShape(), WonCoin.WON_COIN_500.getTeethCount(), WonCoin.WON_COIN_500.getDisplayValue(), WonCoin.WON_COIN_500.getDesign(), WonCoin.WON_COIN_500.getMetalKind()));
        }
        ArrayList<Paper> papers = new ArrayList<>();
        for (int i = 0; i < won1000Count; i++) {
            papers.add(new Paper(WonCash.WON_CASH_1000.getCharacterDesign(), WonCash.WON_CASH_1000.getPaperKind(), WonCash.WON_CASH_1000.getDisplayValue(), WonCash.WON_CASH_1000.getHolographic(), false));
        }
        for (int i = 0; i < won5000Count; i++) {
            papers.add(new Paper(WonCash.WON_CASH_5000.getCharacterDesign(), WonCash.WON_CASH_5000.getPaperKind(), WonCash.WON_CASH_5000.getDisplayValue(), WonCash.WON_CASH_5000.getHolographic(), false));
        }
        for (int i = 0; i < won10000Count; i++) {
            papers.add(new Paper(WonCash.WON_CASH_10000.getCharacterDesign(), WonCash.WON_CASH_10000.getPaperKind(), WonCash.WON_CASH_10000.getDisplayValue(), WonCash.WON_CASH_10000.getHolographic(), false));
        }
        for (int i = 0; i < won50000Count; i++) {
            papers.add(new Paper(WonCash.WON_CASH_50000.getCharacterDesign(), WonCash.WON_CASH_50000.getPaperKind(), WonCash.WON_CASH_50000.getDisplayValue(), WonCash.WON_CASH_50000.getHolographic(), false));
        }
        return new CashPaymentMachine(
                papers,
                metals
        );
    }
}
