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
}
