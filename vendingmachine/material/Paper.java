package vendingmachine.material;

import vendingmachine.won.WonCash;

/**
 * 자판기에는 현금 뿐만 아니라, 위조 지폐, 손상된 지폐,
 * 아니면 그냥 크기만 똑같은 일반 종이 등을 넣을 수 있습니다.
 * 따라서 Cash 즉 현금으로 추상화 하는 것은 다소 부적절하다 생각하였고
 * 이러한 것들을 나타내는 것을 Paper 라고 하였습니다.
 */
public class Paper extends RealObject {
    private String characterDesign;
    private String paperKind;
    private int displayValue;
    private String holographic;
    private boolean isHurt;

    public Paper(String characterDesign, String paperKind, int displayValue, String holographic, boolean isHurt) {
        this.characterDesign = characterDesign;
        this.paperKind = paperKind;
        this.displayValue = displayValue;
        this.holographic = holographic;
        this.isHurt = isHurt;
    }

    public boolean isHurt() {
        return isHurt;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public boolean isWonCashCorrect(WonCash wonCash) {
        return WonCash.getWonCashBySomeInfo(characterDesign, paperKind, displayValue, holographic) == WonCash.WON_CASH_1000;
    }
}
