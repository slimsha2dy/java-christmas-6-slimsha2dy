package christmas.util;

import java.util.HashMap;
import java.util.Map;

public class Utility {
    public static Map<String, Integer> orderParser(String input) {
        Map<String, Integer> order = new HashMap<String, Integer>();
        String[] splittedComma = input.split(",");
        for (String string : splittedComma) {
            String[] splittedHyphen = string.split("-");
            if (order.containsKey(splittedHyphen[0])) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
            }
            order.put(splittedHyphen[0], Integer.parseInt(splittedHyphen[1]));
        }
        return order;
    }
}
