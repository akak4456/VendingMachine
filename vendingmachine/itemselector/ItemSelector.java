package vendingmachine.itemselector;

import vendingmachine.Pair;
import vendingmachine.VendingMachine;
import vendingmachine.VendingMachineVO;
import vendingmachine.material.RealObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class ItemSelector<S extends SelectorDTO, I extends Item> {

    protected ArrayList<ArrayList<RealObject>> itemsInMachine = new ArrayList<>();

    protected List<List<I>> displayItem;

    abstract public void showSelector(VendingMachine<S, I> vendingMachine);

    public final void pushRealObjects(List<RealObject> realObjects) {
        for (RealObject realObject : realObjects) {
            boolean isFound = false;
            for (ArrayList<RealObject> tmp : itemsInMachine) {
                if (!tmp.isEmpty() && tmp.get(0).getClass() == realObject.getClass()) {
                    isFound = true;
                    tmp.add(realObject);
                    break;
                }
            }
            if (!isFound) {
                ArrayList<RealObject> tmp = new ArrayList<>();
                tmp.add(realObject);
                itemsInMachine.add(tmp);
            }
        }
    }

    public final Pair<RealObject, VendingMachineVO> selectItem(S selectDTO, VendingMachineVO paidVO) {
        Item item = selectItemLogic(selectDTO);
        if (item == null) {
            return null;
        }
        if (hasRecipeItemInMachine(item.getRequiredRealObjects())) {
            if (paidVO.isGreaterThanOrEqualsTo(item.getVo())) {
                RealObject target = subtractRealObjectAndMixIfNeeded(item);
                if(target != null) {
                    return new Pair<>(target, item.getVo());
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    protected abstract Item selectItemLogic(S selectDTO);

    protected boolean hasRecipeItemInMachine(List<RealObject> recipes) {
        for (RealObject recipe : recipes) {
            for (int row = 0; row < itemsInMachine.size(); row++) {
                ArrayList<RealObject> tmp = itemsInMachine.get(row);
                if (!tmp.isEmpty() && tmp.get(0).getClass() == recipe.getClass()) {
                    int hasQuantity = 0;
                    for (RealObject t : tmp) {
                        hasQuantity += t.getQuantity();
                    }
                    if (recipe.getQuantity() > hasQuantity) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected RealObject subtractRealObjectAndMixIfNeeded(Item targetItem) {
        for (RealObject recipe : targetItem.getRequiredRealObjects()) {
            boolean isDone = false;
            for (ArrayList<RealObject> items : itemsInMachine) {
                if (!items.isEmpty() && items.get(0).getClass() == recipe.getClass()) {
                    for (RealObject item : items) {
                        if (item.getQuantity() >= recipe.getQuantity()) {
                            item.subtractQuantity(recipe.getQuantity());
                            if (item.getQuantity() == 0) {
                                items.remove(item); // 이거 나중에 문제가 될 것 같은데...
                            }
                            isDone = true;
                            break;
                        } else {
                            recipe.subtractQuantity(item.getQuantity());
                            items.remove(item);
                        }
                    }
                }
                if (isDone) {
                    break;
                }
            }
            if(!isDone) {
                return null;
            }
        }
        return targetItem.getResultRealObject();
    }

}
