package christmas.util;

public enum Constant {
    NOT_IN_MENU(-1),
    EPPETIZER(0),
    MAINMENU(1),
    DESSERT(2),
    BEVERAGE(3),
    CATEGORY_COUNT(4),
    ZERO(0),
    ONE(1),
    THOUSAND(1000),
    HUNDRED(100),
    CHRISTMAS_DAY(25),
    MIN_BENEFIT_PRICE(10000),
    PRESENT_COST(120000),
    CHAMPAIGN_PRICE(25000),
    WEEKDAYS(7),
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3),
    EVENT_PRICE(2023),
    SANTA(20000),
    TREE(10000),
    STAR(5000),
    MAX_MENU_COUNT(20),
    KEY_INDEX(0),
    VALUE_INDEX(1);

    private final int value;

    Constant(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }
}
