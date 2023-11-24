package christmas.view;

import christmas.util.Constant;
import christmas.util.Message;
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
            System.out.println(entry.getKey() + Message.SPACE.get() + entry.getValue() + Message.COUNT.get());
        }
        System.out.println();
    }

    public static void printTotalPrice(int totalPrice) {
        DecimalFormat decimalFormat = new DecimalFormat(Message.PRICE_FORMAT.get());

        System.out.println("<할인 전 총주문 금액>");
        String formattedNumber = decimalFormat.format(totalPrice);
        System.out.println(formattedNumber + Message.WON.get());
        System.out.println();
    }

    public static void printPresent(int totalPrice) {
        System.out.println("<증정 메뉴>");
        if (totalPrice < Constant.PRESENT_COST.get()) {
            System.out.println(Message.NOTHING.get());
            System.out.println();
            return;
        }
        System.out.println(Message.ONE_CHAMPAIGN.get());
        System.out.println();
    }

    public static void printBenefits(Map<String, Integer> benefits) {
        System.out.println("<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println(Message.NOTHING.get());
            System.out.println();
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat(Message.PRICE_FORMAT.get());
        for (Map.Entry<String, Integer> entry : benefits.entrySet()) {
            System.out.print(entry.getKey() + ": -");
            String fomattedNumber = decimalFormat.format(entry.getValue());
            System.out.println(fomattedNumber + Message.WON.get());
        }
        System.out.println();
    }

    public static void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        if (totalBenefitPrice == Constant.ZERO.get()) {
            System.out.println(Message.NOTHING.get());
            System.out.println();
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat(Message.PRICE_FORMAT.get());
        String formattedNumber = decimalFormat.format(totalBenefitPrice);
        System.out.println(Message.HYPHEN.get() + formattedNumber + Message.WON.get());
        System.out.println();
    }

    public static void printTotalPayment(int totalPayment) {
        System.out.println("<할인 후 예상 결제 금액>");
        DecimalFormat decimalFormat = new DecimalFormat(Message.PRICE_FORMAT.get());
        String formattedNumber = decimalFormat.format(totalPayment);
        System.out.println(formattedNumber + Message.WON.get());
        System.out.println();
    }

    public static void printEventBadge(int totalBenefitPrice) {
        System.out.println("<12월 이벤트 배지>");
        if (totalBenefitPrice >= Constant.SANTA.get()) {
            System.out.println("산타");
            return;
        }
        if (totalBenefitPrice >= Constant.TREE.get()) {
            System.out.println("트리");
            return;
        }
        if (totalBenefitPrice >= Constant.STAR.get()) {
            System.out.println("별");
            return;
        }
        System.out.println(Message.NOTHING.get());
    }
}
