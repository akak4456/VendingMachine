import vendingmachine.currency.KoreaWon;
import vendingmachine.VendingMachine;
import vendingmachine.moneysource.cash.WonCash;
import vendingmachine.paymentmachine.CardPaymentMachine;
import vendingmachine.paymentmachine.CashPaymentMachine;

import java.util.List;

public class VendingMachineClient {
    public static void main(String[] args) {
        VendingMachine<KoreaWon> vendingMachine = new VendingMachine<KoreaWon>(
                new CashPaymentMachine(KoreaWon.class),
                new CardPaymentMachine(),
                List.of(
                        List.of(
                                new VendingMachine.Item<>("콜라", new KoreaWon(2000)),
                                new VendingMachine.Item<>("사이다", new KoreaWon(2000)),
                                new VendingMachine.Item<>("물", new KoreaWon(1000)),
                                new VendingMachine.Item<>("커피", new KoreaWon(1000))
                        ),
                        List.of(
                                new VendingMachine.Item<>("껌", new KoreaWon(1000)),
                                new VendingMachine.Item<>("과자", new KoreaWon(2000)),
                                new VendingMachine.Item<>("아이스아메리카노", new KoreaWon(1000)),
                                new VendingMachine.Item<>("탄산음료", new KoreaWon(2000))
                        )
                )
        );
        vendingMachine.printDisplayItems();
        vendingMachine.pushCash(new WonCash(1000));
        vendingMachine.printDisplayItems();
        vendingMachine.selectItem(0,1);
        vendingMachine.selectItem(0,2);
        vendingMachine.printOutletItems();
        vendingMachine.printDisplayItems();
    }
}
