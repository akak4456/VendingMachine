package vendingmachine.won;

public enum WonCash {
    WON_CASH_1000("이황", "폴리머", 1000, "A1000"),
    WON_CASH_5000("이이", "폴리머", 5000, "B5000"),
    WON_CASH_10000("세종대왕", "폴리머", 10000, "C10000"),
    WON_CASH_50000("신사임당", "폴리머", 1000, "D50000");

    private final String characterDesign;
    private final String paperKind;
    private final int displayValue;
    private final String holographic;

    WonCash(String characterDesign, String paperKind, int displayValue, String holographic) {
        this.characterDesign = characterDesign;
        this.paperKind = paperKind;
        this.displayValue = displayValue;
        this.holographic = holographic;
    }

    public String getCharacterDesign() {
        return characterDesign;
    }

    public String getPaperKind() {
        return paperKind;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public String getHolographic() {
        return holographic;
    }

    public static WonCash getWonCashBySomeInfo(String characterDesign, String paperKind, int displayValue, String holographic){
        for(WonCash wonCash : values()){
            if(wonCash.characterDesign.equals(characterDesign) &&
            wonCash.paperKind.equals(paperKind) &&
            wonCash.displayValue == displayValue &&
            wonCash.holographic.equals(holographic)) {
                return wonCash;
            }
        }
        return null;
    }
}
