package vendingmachine.paymentmachine;

import vendingmachine.VendingMachineVO;
import vendingmachine.material.discrete.Metal;
import vendingmachine.material.discrete.Paper;
import vendingmachine.won.WonCash;
import vendingmachine.won.WonCoin;

import java.util.*;

public class CashPaymentMachine {
    private VendingMachineVO userInputVO;
    private HashMap<WonCash, ArrayList<Paper>> paperInMachine; // 당연하겠지만 진짜 현금만 들어있어야 합니다. 다만 실제 세계에 맞추어 그걸 100% 보장하지는 않습니다.
    private HashMap<WonCoin, ArrayList<Metal>> metalInMachine; // 당연하겠지만 진짜 동전만 들어있어야 합니다. 다만 실제 세계에 맞추어 그걸 100% 보장하지는 않습니다.

    public CashPaymentMachine(
            ArrayList<Paper> cashes,
            ArrayList<Metal> coins
    ) {
        userInputVO = new VendingMachineVO();
        paperInMachine = new HashMap<>();
        metalInMachine = new HashMap<>();
        for (Paper cash : cashes) {
            for (WonCash wonCash : WonCash.values()) {
                if (cash.isWonCashCorrect(wonCash)) {
                    if (paperInMachine.containsKey(wonCash)) {
                        paperInMachine.get(wonCash).add(cash);
                    } else {
                        ArrayList<Paper> temp = new ArrayList<>();
                        temp.add(cash);
                        paperInMachine.put(wonCash, temp);
                    }
                    break;
                }
            }
        }
        for (Metal coin : coins) {
            for (WonCoin wonCoin : WonCoin.values()) {
                if (coin.isWonCoinCorrect(wonCoin)) {
                    if (metalInMachine.containsKey(wonCoin)) {
                        metalInMachine.get(wonCoin).add(coin);
                    } else {
                        ArrayList<Metal> temp = new ArrayList<>();
                        temp.add(coin);
                        metalInMachine.put(wonCoin, temp);
                    }
                    break;
                }
            }
        }
    }

    public boolean receivePaper(Paper paper) {
        if (!paper.isHurt()) {
            for (WonCash wonCash : WonCash.values()) {
                if (paper.isWonCashCorrect(wonCash)) {
                    userInputVO.addVO(new VendingMachineVO(wonCash.getDisplayValue()));
                    if (paperInMachine.containsKey(wonCash)) {
                        paperInMachine.get(wonCash).add(paper);
                    } else {
                        ArrayList<Paper> temp = new ArrayList<>();
                        temp.add(paper);
                        paperInMachine.put(wonCash, temp);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean receiveMetal(Metal metal) {
        for (WonCoin wonCoin : WonCoin.values()) {
            if (metal.isWonCoinCorrect(wonCoin)) {
                userInputVO.addVO(new VendingMachineVO(wonCoin.getDisplayValue()));
                if (metalInMachine.containsKey(wonCoin)) {
                    metalInMachine.get(wonCoin).add(metal);
                } else {
                    ArrayList<Metal> temp = new ArrayList<>();
                    temp.add(metal);
                    metalInMachine.put(wonCoin, temp);
                }
                return true;
            }
        }
        return false;
    }

    public boolean pay(VendingMachineVO vo) {
        return this.userInputVO.subtractVOIfPossible(vo);
    }

    public List<Paper> changeByCash() {
        ArrayList<Paper> ret = new ArrayList<>();
        for (WonCash wonCash : Arrays.stream(WonCash.values()).sorted(Comparator.comparingInt(WonCash::getDisplayValue)).toList()) {
            if (paperInMachine.containsKey(wonCash)) {
                while (paperInMachine.get(wonCash).size() > 0 && userInputVO.isGreaterThanOrEqualsTo(new VendingMachineVO(wonCash.getDisplayValue()))) {
                    userInputVO.subtractVOIfPossible(new VendingMachineVO(wonCash.getDisplayValue()));
                    ret.add(paperInMachine.get(wonCash).get(0));
                    paperInMachine.get(wonCash).remove(0);
                }
            }
        }
        return ret;
    }

    public List<Metal> changeByCoin() {
        ArrayList<Metal> ret = new ArrayList<>();
        for (WonCoin wonCoin : Arrays.stream(WonCoin.values()).sorted(Comparator.comparingInt(WonCoin::getDisplayValue)).toList()) {
            if (metalInMachine.containsKey(wonCoin)) {
                while (metalInMachine.get(wonCoin).size() > 0 && userInputVO.isGreaterThanOrEqualsTo(new VendingMachineVO(wonCoin.getDisplayValue()))) {
                    userInputVO.subtractVOIfPossible(new VendingMachineVO(wonCoin.getDisplayValue()));
                    ret.add(metalInMachine.get(wonCoin).get(0));
                    metalInMachine.get(wonCoin).remove(0);
                }
            }
        }
        return ret;
    }

    public boolean isAllChange() {
        return userInputVO.getValue() == 0;
    }

    public String displayCurrentUserInputMoney() {
        return userInputVO.toString();
    }

    public boolean isUserVOGreaterThanOrEqualsTo(VendingMachineVO vo) {
        return userInputVO.isGreaterThanOrEqualsTo(vo);
    }

    public VendingMachineVO getUserInputVO() {
        return userInputVO;
    }
}
