package vendingmachine.paymentmachine;

import vendingmachine.VendingMachineVO;
import vendingmachine.material.discrete.IC;
import vendingmachine.material.discrete.Magnetic;
import vendingmachine.material.discrete.Plastic;

public class CardPaymentMachine {
    private IC ic = null;
    private Magnetic magnetic = null;

    public boolean receivePlastic(Plastic plastic) {
        if (plastic.getIc() != null) {
            ic = plastic.getIc();
            return true;
        }
        if (plastic.getMagnetic() != null) {
            magnetic = plastic.getMagnetic();
            return true;
        }
        return false;
    }

    public boolean pay(VendingMachineVO vo) {
        // 실제로는 약간의 변환 과정이 있어야 한다.
        // 예컨대 자판기가 원화를 받고 만약에 카드가 달러 결제를 지원한다면 환전을 한다는 식의...
        // 일단 모두 원화인것을 기준으로 한다.
        if (ic != null) {
            return ic.getAccount().subtractAmountIfPossible(vo.getValue());
        }
        if (magnetic != null) {
            return magnetic.getAccount().subtractAmountIfPossible(vo.getValue());
        }
        return false;
    }

    public void change() {
        ic = null;
        magnetic = null;
    }

    public boolean isCardAvailable() {
        return ic != null || magnetic != null;
    }
}
