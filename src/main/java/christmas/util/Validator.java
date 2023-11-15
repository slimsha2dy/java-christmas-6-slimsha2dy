package christmas.util;

import christmas.util.ErrorMessage;
import christmas.util.Utility;

import java.util.Map;
import java.util.List;

public class Validator {
    private final static char MIN_DIGIT = '0';
    private final static char MAX_DIGIT = '9';
    private final static char CHAR_HYPHEN = '-';
    private final static char CHAR_COMMA = ',';
    private final static int MIN_DAY = 1;
    private final static int MAX_DAY = 31;
    private final static int IS_TWO = 2;

    public static void validateDate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
        }
        for (int i = Constant.ZERO.get(); i < input.length(); ++i) {
            if (input.charAt(i) < MIN_DIGIT || input.charAt(i) > MAX_DIGIT) {
                throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
            }
        }
        int date = Integer.parseInt(input);
        if (date < MIN_DAY || date > MAX_DAY) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
        }
    }

    public static void validateOrder(String input) {
        // split할 경우 마지막의 빈 문자열을 사라지므로 별도로 예외 처리
        if (input.isEmpty() || input.charAt(input.length() - Constant.ONE.get()) == CHAR_COMMA) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
        }
        String[] splittedComma = input.split(Message.COMMA.get());
        if (splittedComma.length == Constant.ZERO.get()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
        }
        for (String string : splittedComma) {
            if (validateFormat(string)) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
            }
        }
    }

    public static boolean validateMenu(Map<String, Integer> userOrder, List<Map<String, Integer>> menu) {
        int[] countCategory = new int[Constant.CATEGORY_COUNT.get()];
        for (Map.Entry<String, Integer> entry : userOrder.entrySet()) {
            if (entry.getValue() <= Constant.ZERO.get()) {
                return true;
            }
            int category = Utility.getCategory(entry.getKey(), menu);
            if (category == Constant.NOT_IN_MENU.get()) {
                return true;
            }
            countCategory[category] += entry.getValue();
        }
        if (validateCount(countCategory)) {
            return true;
        }
        return false;
    }

    private static boolean validateCount(int[] countCategory) {
        // 음료만 시킨 경우 예외 처리
        if (countCategory[Constant.EPPETIZER.get()] == Constant.ZERO.get()
                && countCategory[Constant.MAINMENU.get()] == Constant.ZERO.get()
                && countCategory[Constant.DESSERT.get()] == Constant.ZERO.get()) {
            return true;
        }
        int totalCount = Constant.ZERO.get();
        for (int count : countCategory) {
            totalCount += count;
        }
        if (totalCount > Constant.MAX_MENU_COUNT.get()) {
            return true;
        }
        return false;
    }

    private static boolean validateFormat(String input) {
        if (input.isEmpty() || input.charAt(input.length() - Constant.ONE.get()) == CHAR_HYPHEN) {
            return true;
        }
        String[] splittedHyphen = input.split(Message.HYPHEN.get());
        if (splittedHyphen.length != IS_TWO) {
            return true;
        }
        for (int i = Constant.ZERO.get(); i < splittedHyphen[Constant.VALUE_INDEX.get()].length(); ++i) {
            if (splittedHyphen[Constant.VALUE_INDEX.get()].charAt(i) < MIN_DIGIT
                    || splittedHyphen[Constant.VALUE_INDEX.get()].charAt(i) > MAX_DIGIT) {
                return true;
            }
        }
        return false;
    }
}
