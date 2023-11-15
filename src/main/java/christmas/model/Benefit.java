package christmas.model;

import christmas.util.Constant;
import java.util.HashMap;
import java.util.Map;

public class Benefit {
    private int orderDate;
    private int[] categoryCount;
    Map<String, Integer> benefits;

    public Benefit(int orderDate, int[] categoryCount, int totalPrice) {
        this.orderDate = orderDate;
        this.categoryCount = categoryCount;
        benefits = new HashMap<>();
        if (totalPrice < 10000) {
            return;
        }
        christmasBenefit();
        weekBenefit();
        starBenefit();
        if (totalPrice >= 120000) {
            presentBenefit();
        }
    }
    public Map<String, Integer> getBenefits() {
        return this.benefits;
    }

    public int getTotalBenefitPrice() {
        int totalBenefitPrice = 0;
        for (Map.Entry<String, Integer> entry : this.benefits.entrySet()) {
            int benefit = entry.getValue();
            totalBenefitPrice += benefit;
        }
        return totalBenefitPrice;
    }

    public int getPresentPrice() {
        if (this.benefits.containsKey("증정 이벤트")) {
            return 25000;
        }
        return 0;
    }

    private void christmasBenefit() {
        if (orderDate > 25) {
            return;
        }
        int benefit = 1000 + (orderDate - 1) * 100;
        benefits.put("크리스마스 디데이 할인", benefit);
    }

    private void weekBenefit() {
        if (orderDate % 7 == 1 || orderDate % 7 == 2) {
            int benefit = 2023 * categoryCount[Constant.MAINMENU.get()];
            benefits.put("주말 할인", benefit);
            return;
        }
        int benefit = 2023 * categoryCount[Constant.DESSERT.get()];
        benefits.put("평일 할인", benefit);
    }

    private void starBenefit() {
        if (orderDate != 25 && orderDate % 7 != 3) {
            return;
        }
        benefits.put("특별 할인", 1000);
    }

    private void presentBenefit() {
        benefits.put("증정 이벤트", 25000);
    }
}
