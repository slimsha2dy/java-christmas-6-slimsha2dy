package christmas.view;

import christmas.util.Message;
import christmas.util.Validator;
import christmas.util.Utility;

import java.util.Map;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        System.out.println(Message.READ_DATE_MESSAGE.get());
        String input = Console.readLine();
        try {
            Validator.validateDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDate();
        }
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> readOrder() {
        System.out.println(Message.READ_ORDER_MESSAGE.get());
        String input = Console.readLine();
        Map<String, Integer> order;
        try {
            Validator.validateOrder(input);
            order = Utility.orderParser(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readOrder();
        }
        return order;
    }
}
