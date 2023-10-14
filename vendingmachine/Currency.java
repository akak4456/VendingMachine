package vendingmachine;

/**
 * 원래 Currency 를 abstract 클래스로 설계후 USD 를 나타내는 클래스, KRW 를 나타내는 클래스를 만들어서
 * Currency 를 생성하려고 했습니다. 하지만 이렇게 작성하면 Currency 끼리 더하는 함수를 만들 때에나
 * Currency 끼리 비교하는 함수를 만들 때에 오직 같은 종류의 Currency (ex KRW Currency) 에 대해서만 연산이
 * 이루어져야 하는데 이렇게 하면 예외 처리가 다소 번잡하게 됩니다. 또한 실제 생성은 Currency 가 아니라, KRW Currency,
 * USD Currency 가 생성이 되어야 하는데 이게 다소 복잡하게 됩니다. 따라서 Currency 는 enum 으로 나타내고
 * 실질적인 화폐 의미는 MoneySource 에서 나타내도록 변경하였습니다.
 */
public enum Currency {
    USD("달러"),
    KRW("원");

    private final String unit;

    public String getUnit() {
        return unit;
    }

    Currency(String unit) {
        this.unit = unit;
    }

}
