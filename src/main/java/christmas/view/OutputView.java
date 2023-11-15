package christmas.view;

import java.util.Map;

import java.text.DecimalFormat;

public class OutputView {
    public static void printPreview(int orderDate) {
        System.out.println("12월 " + orderDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public static void printOrder(Map<String, Integer> userOrder) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> entry : userOrder.entrySet()) {
            System.out.println(entry.getKey() + ' ' + entry.getValue());
        }
        System.out.println();
    }

    public static void printTotalPrice(int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        System.out.println("<할인 전 총주문 금액>");
        String formattedNumber = decimalFormat.format(totalPrice);
        System.out.println(formattedNumber + "원");
    }
}
