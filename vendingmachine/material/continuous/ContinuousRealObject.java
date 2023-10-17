package vendingmachine.material.continuous;

import vendingmachine.material.RealObject;

/**
 * 연속적인 물체를 추상화
 * 물, 소금 같이 손에 잘 안잡히거나 명확하게 개수로 셀 수 없는 것
 */
public class ContinuousRealObject extends RealObject {

    public ContinuousRealObject(int quantity) {
        super(quantity);
    }
}
