package vendingmachine.material;

/**
 * 실제 세계에서 존재하는 종이, 금속, 우유, 콜라 등을 모사한 클래스입니다.
 */
public abstract class RealObject {
    private int quantity;

    public RealObject(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void subtractQuantity(int quantity) {
        this.quantity -= quantity;
    }
}
