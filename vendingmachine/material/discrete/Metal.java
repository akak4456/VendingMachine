package vendingmachine.material.discrete;

import vendingmachine.material.RealObject;
import vendingmachine.won.WonCoin;

/**
 * 자판기에는 동전 뿐만 아니라, 위조 동전, 그냥 금속 등을 넣을 수 있습니다.
 * 따라서 Coin 즉 동전으로 추상화 하는 것은 다소 부적절하다 생각하였고
 * 이러한 것들을 나타내는 것을 Metal 이라고 하였습니다.
 */
public class Metal extends DiscreteRealObject {
    private String shape;
    private int teethCount;
    private int displayValue;
    private String design;
    private String metalKind;

    public Metal(String shape, int teethCount, int displayValue, String design, String metalKind) {
        this.shape = shape;
        this.teethCount = teethCount;
        this.displayValue = displayValue;
        this.design = design;
        this.metalKind = metalKind;
    }

    public boolean isWonCoinCorrect(WonCoin coin) {
        return WonCoin.getWonCoinBySomeInfo(shape, teethCount, displayValue, design, metalKind) == coin;
    }
}
