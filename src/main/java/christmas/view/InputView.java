package christmas.view;

import christmas.util.Validator;
import christmas.util.Utility;

import java.util.Map;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
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
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
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
