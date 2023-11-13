package christmas.util;

public enum Constant {
    NOT_IN_MENU(-1),
    EPPETIZER(0),
    MAINMENU(1),
    DESSERT(2),
    BEVERAGE(3),
    CATEGORY_COUNT(4),
    ZERO(0);

    private final int value;

    Constant(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }
}
