package christmas.model;

import christmas.util.Constant;
import christmas.util.Message;

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
        if (totalPrice < Constant.MIN_BENEFIT_PRICE.get()) {
            return;
        }
        christmasBenefit();
        weekBenefit();
        starBenefit();
        if (totalPrice >= Constant.PRESENT_COST.get()) {
            presentBenefit();
        }
    }

    public Map<String, Integer> getBenefits() {
        return this.benefits;
    }

    public int getTotalBenefitPrice() {
        int totalBenefitPrice = Constant.ZERO.get();
        for (Map.Entry<String, Integer> entry : this.benefits.entrySet()) {
            int benefit = entry.getValue();
            totalBenefitPrice += benefit;
        }
        return totalBenefitPrice;
    }

    public int getPresentPrice() {
        if (this.benefits.containsKey(Message.PRESENT_BENEFIT.get())) {
            return Constant.CHAMPAIGN_PRICE.get();
        }
        return Constant.ZERO.get();
    }

    private void christmasBenefit() {
        if (orderDate > Constant.CHRISTMAS_DAY.get()) {
            return;
        }
        int benefit = Constant.THOUSAND.get() + (orderDate - Constant.ONE.get()) * Constant.HUNDRED.get();
        benefits.put(Message.D_DAY_BENEFIT.get(), benefit);
    }

    private void weekBenefit() {
        if (orderDate % Constant.WEEKDAYS.get() == Constant.FRIDAY.get()
                || orderDate % Constant.WEEKDAYS.get() == Constant.SATURDAY.get()) {
            int benefit = Constant.EVENT_PRICE.get() * categoryCount[Constant.MAINMENU.get()];
            benefits.put(Message.WEEKEND_BENEFIT.get(), benefit);
            return;
        }
        int benefit = Constant.EVENT_PRICE.get() * categoryCount[Constant.DESSERT.get()];
        benefits.put(Message.WEEKDAY_BENEFIT.get(), benefit);
    }

    private void starBenefit() {
        if (orderDate != Constant.CHRISTMAS_DAY.get()
                && orderDate % Constant.WEEKDAYS.get() != Constant.SUNDAY.get()) {
            return;
        }
        benefits.put(Message.STAR_BENEFIT.get(), Constant.THOUSAND.get());
    }

    private void presentBenefit() {
        benefits.put(Message.PRESENT_BENEFIT.get(), Constant.CHAMPAIGN_PRICE.get());
    }
}
