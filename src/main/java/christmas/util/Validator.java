package christmas.util;

import christmas.util.ErrorMessage;
import christmas.util.Utility;

import java.util.Map;
import java.util.List;

public class Validator {
    public static void validateDate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
        }
        for (int i = 0; i < input.length(); ++i) {
            if (input.charAt(i) < '0' || input.charAt(i) > '9') {
                throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
            }
        }
        int date = Integer.parseInt(input);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.DATE_ERROR.get());
        }
    }

    public static void validateOrder(String input) {
        // split할 경우 마지막의 빈 문자열을 사라지므로 별도로 예외 처리
        if (input.isEmpty() || input.charAt(input.length() - 1) == ',') {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
        }
        String[] splittedComma = input.split(",");
        if (splittedComma.length == 0) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
        }
        for (String string : splittedComma) {
            if (validateFormat(string)) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
            }
        }
    }

    public static boolean validateMenu(Map<String, Integer> userOrder, List<Map<String, Integer>> menu) {
        int[] countCategory = new int[4];
        for (Map.Entry<String, Integer> entry : userOrder.entrySet()) {
            if (entry.getValue() <= 0) {
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
        if (countCategory[Constant.EPPETIZER.get()] == 0
                && countCategory[Constant.MAINMENU.get()] == 0 && countCategory[Constant.DESSERT.get()] == 0) {
            return true;
        }
        int totalCount = 0;
        for (int count : countCategory) {
            totalCount += count;
        }
        if (totalCount > 20) {
            return true;
        }
        return false;
    }

    private static boolean validateFormat(String input) {
        if (input.isEmpty() || input.charAt(input.length() - 1) == '-') {
            return true;
        }
        String[] splittedHyphen = input.split("-");
        if (splittedHyphen.length != 2) {
            return true;
        }
        for (int i = 0; i < splittedHyphen[1].length(); ++i) {
            if (splittedHyphen[1].charAt(i) < '0' || splittedHyphen[1].charAt(i) > '9') {
                return true;
            }
        }
        return false;
    }
}
