package christmas.util;

import christmas.util.ErrorMessage;

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
