package christmas;

import christmas.model.OrderMenu;
import christmas.util.Validator;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {
    private List<Map<String, Integer>> menu = new ArrayList<>(List.of(
            Map.of("양송이수프", 6000, "타파스", 5500, "시저샐러드", 8000),
            Map.of("티본스테이크", 55000, "바비큐립", 54000, "해산물파스타", 35000, "크리스마스파스타", 25000),
            Map.of("초코케이크", 15000, "아이스크림", 5000),
            Map.of("제로콜라", 3000, "레드와인", 60000, "샴페인", 25000)
    ));

    @DisplayName("입력된 날짜가 비어있으면 예외가 발생한다.")
    @Test
    void isDateNoneTest() {
        assertThatThrownBy(() -> Validator.validateDate(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 날짜가 숫자가 아니면 예외가 발생한다.")
    @Test
    void isDateInvalidTest() {
        assertThatThrownBy(() -> Validator.validateDate("1a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 날짜가 범위를 벗어나면 예외가 발생한다.")
    @Test
    void dateRangeTest() {
        assertThatThrownBy(() -> Validator.validateDate("123"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 주문이 비어있으면 예외가 발생한다.")
    @Test
    void isOrderNoneTest() {
        assertThatThrownBy(() -> Validator.validateOrder(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 주문 중 빈 문자열이 있으면 예외가 발생한다.")
    @Test
    void isParsedNoneTest() {
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-2,레드와인-1,"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder(",해산물파스타-2,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-2,레드와인-1,"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-2,,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder(",,,"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 포맷이 올바르지 않으면 예외가 발생한다.")
    @Test
    void isFormatWrongTest() {
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-1-1,제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-1,제로콜라-"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-1,---"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("개수에 숫자가 아닌 값이 들어가면 예외가 발생한다.")
    @Test
    void isNotNumberTest() {
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-레드와인"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-1,제로콜라-레드와인"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Validator.validateOrder("해산물파스타-1,제로콜라-2레드와인"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴를 주문하면 예외가 발생한다.")
    @Test
    void isOrderInMenu() {
        Map<String, Integer> order1 = Map.of("똠얌꿍", 1);
        Map<String, Integer> order2 = Map.of("양송이수프", 2, "연저육찜", 1);
        Map<String, Integer> order3 = Map.of("양송이수프", 1, "아이스크림", 1);

        assertThat(Validator.validateMenu(order1, menu)).isEqualTo(true);
        assertThat(Validator.validateMenu(order2, menu)).isEqualTo(true);
        assertThat(Validator.validateMenu(order3, menu)).isEqualTo(false);
    }

    @DisplayName("0개 이하인 개수가 있다면 예외가 발생한다.")
    @Test
    void isCountValid() {
        Map<String, Integer> order1 = Map.of("아이스크림", 0);
        Map<String, Integer> order2 = Map.of("양송이수프", 1, "아이스크림", 0);

        assertThat(Validator.validateMenu(order1, menu)).isEqualTo(true);
        assertThat(Validator.validateMenu(order2, menu)).isEqualTo(true);
    }

    @DisplayName("20개가 넘어가면 예외가 발생한다.")
    @Test
    void isUnderTwenty() {
        Map<String, Integer> order3 = Map.of("양송이수프", 21);
        Map<String, Integer> order4 = Map.of("양송이수프", 10, "아이스크림", 11);

        assertThat(Validator.validateMenu(order3, menu)).isEqualTo(true);
        assertThat(Validator.validateMenu(order4, menu)).isEqualTo(true);
    }

    @DisplayName("음료만 주문하면 예외가 발생한다.")
    @Test
    void isOnlyBeverage() {
        Map<String, Integer> order3 = Map.of("제로콜라", 1);
        Map<String, Integer> order4 = Map.of("제로콜라", 3, "레드와인", 1);

        assertThat(Validator.validateMenu(order3, menu)).isEqualTo(true);
        assertThat(Validator.validateMenu(order4, menu)).isEqualTo(true);
    }
}
