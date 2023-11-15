package christmas.util;

public enum Message {
    D_DAY_BENEFIT("크리스마스 디데이 할인"),
    WEEKEND_BENEFIT("주말 할인"),
    WEEKDAY_BENEFIT("평일 할인"),
    PRESENT_BENEFIT("증정 이벤트"),
    STAR_BENEFIT("특별 할인"),
    COMMA(","),
    HYPHEN("-"),
    SPACE(" "),
    READ_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    READ_ORDER_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PRICE_FORMAT("#,###"),
    WON("원"),
    COUNT("개"),
    NOTHING("없음"),
    ONE_CHAMPAIGN("샴페인 1개");

    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String get() {
        return this.value;
    }
}
