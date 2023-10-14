import vendingmachine.Currency;
import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineConsole;
import vendingmachine.item.VendingMachineConsoleItem;
import vendingmachine.moneysource.card.CheckCard;
import vendingmachine.moneysource.card.CreditCard;
import vendingmachine.moneysource.cash.BadDollarCash;
import vendingmachine.moneysource.cash.BadWonCash;
import vendingmachine.moneysource.cash.DollarCash;
import vendingmachine.moneysource.cash.WonCash;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;

import java.util.List;

public class VendingMachineClient {
    public static void main(String[] args) {
        VendingMachineClient client = new VendingMachineClient();
        // client.storyKRW();
        client.storyUSD();
    }

    public void storyKRW() {
        VendingMachine<VendingMachineConsoleItem> vendingMachine = new VendingMachineConsole(
                new CashPaymentMachine(WonCash.class),
                new CardPaymentMachine(Currency.KRW),
                List.of(
                        List.of(
                                new VendingMachineConsoleItem("콜라", Currency.KRW, 2000),
                                new VendingMachineConsoleItem("사이다", Currency.KRW, 2000),
                                new VendingMachineConsoleItem("물", Currency.KRW, 1000),
                                new VendingMachineConsoleItem("커피", Currency.KRW, 1000)
                        ),
                        List.of(
                                new VendingMachineConsoleItem("껌", Currency.KRW, 1000),
                                new VendingMachineConsoleItem("과자", Currency.KRW, 2000),
                                new VendingMachineConsoleItem("아이스아메리카노", Currency.KRW, 1000),
                                new VendingMachineConsoleItem("탄산음료", Currency.KRW, 2000)
                        )
                )
        );
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new WonCash(1000));
        vendingMachine.pushCash(new BadWonCash(1000));
        vendingMachine.pushCash(new DollarCash(1000));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0, 1);
        vendingMachine.selectItem(0, 2);
        vendingMachine.printOutletItems();
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new WonCash(2000));
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new WonCash(2000));
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new BadWonCash(2000));
        vendingMachine.selectItem(0, 0);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(1, 1);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.pushCard(new CheckCard(Currency.KRW, 3000));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.printOutletItems();
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.selectItem(0,2);
        vendingMachine.pushCard(new CreditCard(Currency.KRW, 0,-5000));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(1,0);
        vendingMachine.selectItem(1,1);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(0,1);
        vendingMachine.selectItem(0,1);
        vendingMachine.pushCard(new CreditCard(Currency.USD, 0,-5000));
        vendingMachine.printDisplayItems();
    }

    public void storyUSD() {
        VendingMachine<VendingMachineConsoleItem> vendingMachine = new VendingMachineConsole(
                new CashPaymentMachine(DollarCash.class),
                new CardPaymentMachine(Currency.USD),
                List.of(
                        List.of(
                                new VendingMachineConsoleItem("콜라", Currency.USD, 2),
                                new VendingMachineConsoleItem("사이다", Currency.USD, 2),
                                new VendingMachineConsoleItem("물", Currency.USD, 1),
                                new VendingMachineConsoleItem("커피", Currency.USD, 1)
                        ),
                        List.of(
                                new VendingMachineConsoleItem("껌", Currency.USD, 1),
                                new VendingMachineConsoleItem("과자", Currency.USD, 2),
                                new VendingMachineConsoleItem("아이스아메리카노", Currency.USD, 1),
                                new VendingMachineConsoleItem("탄산음료", Currency.USD, 2)
                        )
                )
        );
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new WonCash(1000));
        vendingMachine.pushCash(new BadWonCash(1000));
        vendingMachine.pushCash(new DollarCash(1));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0, 1);
        vendingMachine.selectItem(0, 2);
        vendingMachine.printOutletItems();
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new DollarCash(2));
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new DollarCash(2));
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new BadDollarCash(2));
        vendingMachine.selectItem(0, 0);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(1, 1);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.pushCard(new CheckCard(Currency.USD, 3));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.printOutletItems();
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,0);
        vendingMachine.selectItem(0,2);
        vendingMachine.pushCard(new CreditCard(Currency.USD, 0,-5));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(1,0);
        vendingMachine.selectItem(1,1);
        vendingMachine.printOutletItems();
        vendingMachine.selectItem(0,1);
        vendingMachine.selectItem(0,1);
        vendingMachine.pushCard(new CreditCard(Currency.KRW, 0,-5000));
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new DollarCash(3));
        vendingMachine.selectItem(0,0);
        vendingMachine.printOutletItems();
        vendingMachine.change();
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,1);
    }
}
