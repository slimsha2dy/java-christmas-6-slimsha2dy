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
        System.out.println();
    }

    public static void printPresent(int totalPrice) {
        System.out.println("<증정 메뉴>");
        if (totalPrice < 120000) {
            System.out.println("없음");
            return;
        }
        System.out.println("샴페인 1개");
        System.out.println();
    }

    public static void printBenefits(Map<String, Integer> benefits) {
        System.out.println("<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println("없음");
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        for (Map.Entry<String, Integer> entry : benefits.entrySet()) {
            System.out.print(entry.getKey() + ": -");
            String fomattedNumber = decimalFormat.format(entry.getValue());
            System.out.println(fomattedNumber + "원");
        }
        System.out.println();
    }
}
