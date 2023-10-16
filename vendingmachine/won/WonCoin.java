package vendingmachine.won;

public enum WonCoin {
    WON_COIN_10("원",10,10,"다보탑","구리"),
    WON_COIN_50("원",20,50,"벼이삭","알루미늄"),
    WON_COIN_100("원",25,100,"이순신","철"),
    WON_COIN_500("원",30,500,"학","철");
    private final String shape;
    private final int teethCount;
    private final int displayValue;
    private final String design;
    private final String metalKind;

    WonCoin(String shape, int teethCount, int displayValue, String design, String metalKind) {
        this.shape = shape;
        this.teethCount = teethCount;
        this.displayValue = displayValue;
        this.design = design;
        this.metalKind = metalKind;
    }

    public String getShape() {
        return shape;
    }

    public int getTeethCount() {
        return teethCount;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public String getDesign() {
        return design;
    }

    public String getMetalKind() {
        return metalKind;
    }

    public static WonCoin getWonCoinBySomeInfo(String shape, int teethCount, int displayValue, String design, String metalKind) {
        for(WonCoin coin : values()) {
            if(coin.shape.equals(shape) &&
            coin.teethCount == teethCount &&
            coin.displayValue == displayValue &&
            coin.design.equals(design) &&
            coin.metalKind.equals(metalKind)) {
                return coin;
            }
        }
        return null;
    }
}
