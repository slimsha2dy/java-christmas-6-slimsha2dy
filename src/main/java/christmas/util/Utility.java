package christmas.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
    public static Map<String, Integer> orderParser(String input) {
        Map<String, Integer> order = new HashMap<String, Integer>();
        String[] splittedComma = input.split(Message.COMMA.get());
        for (String string : splittedComma) {
            String[] splittedHyphen = string.split(Message.HYPHEN.get());
            if (order.containsKey(splittedHyphen[Constant.KEY_INDEX.get()])) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
            }
            order.put(splittedHyphen[Constant.KEY_INDEX.get()]
                    , Integer.parseInt(splittedHyphen[Constant.VALUE_INDEX.get()]));
        }
        return order;
    }

    public static int getCategory(String food, List<Map<String, Integer>> menu) {
        for (int category = Constant.EPPETIZER.get(); category <= Constant.BEVERAGE.get(); ++category) {
            if (menu.get(category).containsKey(food)) {
                return category;
            }
        }
        return Constant.NOT_IN_MENU.get();
    }
}
