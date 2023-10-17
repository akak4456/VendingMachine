package vendingmachine.material.discrete;

import vendingmachine.material.RealObject;

/**
 * 불연속적인 물체를 추상화
 * 콜라, 물병과 같이 손에 잡히는 물체
 */
public class DiscreteRealObject extends RealObject {
    public DiscreteRealObject() {
        super(1);
    }
}
