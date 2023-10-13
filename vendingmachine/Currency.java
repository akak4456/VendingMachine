package vendingmachine;

import java.lang.reflect.InvocationTargetException;

public enum Currency {
    USD("달러"),
    KRW("원");

    final String unit;

    Currency(String unit) {
        this.unit = unit;
    }

}
