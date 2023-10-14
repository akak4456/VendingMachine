package vendingmachine.moneysource.card;

import vendingmachine.Currency;
import vendingmachine.moneysource.MoneySource;

import java.util.ArrayList;
import java.util.List;

/**
 * Card 에는 체크카드와 신용카드가 있습니다.
 * CheckCard 는 underBound 가 0입니다. 따라서 expressionValue 즉 카드에 남은 잔액에 0보다 커야지만 pay 를 진행할 수 있습니다.
 * CreditCard 는 underBound 가 음수입니다. 따라서 expressionValue 가 0보다 작아도 pay 를 진행 할 수 있습니다.
 * subtract 등으로 결제 진행시에 usedList 에 결제 내역을 담도록 하였습니다.
 */
public abstract class Card extends MoneySource {

    private int underBound;
    private List<Integer> usedList = new ArrayList<>();

    public Card(Currency currency, int expressionValue, int underBound) {
        super(currency, expressionValue);
        this.underBound = underBound;
    }

    public int getUnderBound() {
        return this.underBound;
    }

    @Override
    public boolean subtractExpressionValueIfPossible(Currency currency, int expressionValue, int underBound) {
        boolean result = super.subtractExpressionValueIfPossible(currency, expressionValue, underBound);
        if (result) {
            usedList.add(expressionValue);
        }
        return result;
    }

}
