package christmas.model;

import christmas.util.Constant;
import christmas.util.ErrorMessage;
import christmas.util.Utility;
import christmas.util.Validator;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class OrderMenu {
    private final List<Map<String, Integer>> menu;

    private int orderDate;
    private Map<String, Integer> userOrder;

    public OrderMenu() {
        menu = new ArrayList<>(List.of(
                // EPPETIZER 0
                Map.of("양송이수프", 6000, "타파스", 5500, "시저샐러드", 8000),
                // MAINMENU 1
                Map.of("티본스테이크", 55000, "바비큐립", 54000, "해산물파스타", 35000, "크리스마스파스타", 25000),
                // DESSERT 2
                Map.of("초코케이크", 15000, "아이스크림", 5000),
                // BEVERAGE 3
                Map.of("제로콜라", 3000, "레드와인", 60000, "샴페인", 25000)
        ));
    }

    public int getOrderDate() {
        return this.orderDate;
    }

    public Map<String, Integer> getUserOrder() {
        return this.userOrder;
    }

    public int getTotalPrice() {
        int totalPrice = Constant.ZERO.get();
        for (Map.Entry<String, Integer> entry : this.userOrder.entrySet()) {
            int category = Utility.getCategory(entry.getKey(), menu);
            int price = menu.get(category).get(entry.getKey());
            totalPrice += price * entry.getValue();
        }
        return totalPrice;
    }

    public int[] getCategoryCount() {
        int[] categoryCount = new int[Constant.CATEGORY_COUNT.get()];
        for (Map.Entry<String, Integer> entry : this.userOrder.entrySet()) {
            int category = Utility.getCategory(entry.getKey(), menu);
            categoryCount[category] += entry.getValue();
        }
        return categoryCount;
    }

    public void setOrderDate(int date) {
        this.orderDate = date;
    }

    public void setUserOrder(Map<String, Integer> userOrder) {
        if (Validator.validateMenu(userOrder, this.menu)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR.get());
        }
        this.userOrder = userOrder;
    }
}
