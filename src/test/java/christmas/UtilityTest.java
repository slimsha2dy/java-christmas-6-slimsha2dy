package christmas;

import java.util.Map;

import christmas.util.Utility;
import christmas.util.Validator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

public class UtilityTest {
    @DisplayName("중복된 메뉴를 넣으면 예외가 발생한다.")
    @Test
    void isMenuDuplicated() {
        assertThatThrownBy(() -> Utility.orderParser("티본스테이크-1,티본스테이크-1"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Utility.orderParser("티본스테이크-1,바비큐립-1,초코케이크-2,티본스테이크-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파싱이 잘 되는지 확인")
    @Test
    void isParsingWell() {
        Map<String, Integer> parsed = Utility.orderParser("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        assertThat(parsed.size()).isEqualTo(4);
        assertThat(parsed.get("티본스테이크")).isEqualTo(1);
        assertThat(parsed.get("바비큐립")).isEqualTo(1);
        assertThat(parsed.get("초코케이크")).isEqualTo(2);
        assertThat(parsed.get("제로콜라")).isEqualTo(1);
    }
}
