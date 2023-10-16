package vendingmachine;

/**
 * 자판기에서 물건값을 나타내는 클래스
 * 원화를 기준으로 한다. 다시말해 value 가 1000 이면 1000원 임을 의미한다.
 */
public class VendingMachineVO {
    private int value;

    public VendingMachineVO() {
        value = 0;
    }

    public VendingMachineVO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void addVO(VendingMachineVO vo) {
        this.value += vo.getValue();
    }

    public boolean subtractVOIfPossible(VendingMachineVO vo) {
        if (this.value >= vo.getValue()) {
            this.value -= vo.getValue();
            return true;
        }
        return false;
    }

    public boolean isGreaterThanOrEqualsTo(VendingMachineVO vo) {
        return this.value >= vo.value;
    }

    @Override
    public String toString() {
        return this.value +"원";
    }
}
