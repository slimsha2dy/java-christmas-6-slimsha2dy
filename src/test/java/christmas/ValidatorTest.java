package christmas;

import christmas.util.Validator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
public class ValidatorTest {

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
}
