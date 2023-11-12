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
}
