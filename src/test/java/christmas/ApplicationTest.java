package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 케이스() {
        assertSimpleTest(() -> {
            run("25", "타파스-1,제로콜라-2,아이스크림-1,크리스마스파스타-1");
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR + "41,500원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원",
                    "평일 할인: -2,023원",
                    "특별 할인: -1,000원",
                    "<총혜택 금액>" + LINE_SEPARATOR + "-6,423원",
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "35,077원",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "별"
            );
        });
    }

    @Test
    void 케이스_2() {
        assertSimpleTest(() -> {
            run("31", "아이스크림-2");
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR + "10,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "없음",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "<총혜택 금액>" + LINE_SEPARATOR + "-5,046원",
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "4,954원",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "별"
            );
        });
    }

    @Test
    void 케이스_3() {
        assertSimpleTest(() -> {
            run("1", "시저샐러드-1,티본스테이크-2,초코케이크-2,레드와인-1");
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR + "208,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
                    "주말 할인: -4,046원",
                    "증정 이벤트: -25,000원",
                    "<총혜택 금액>" + LINE_SEPARATOR + "-29,046원",
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "178,954원",
                    "<12월 이벤트 배지>" + LINE_SEPARATOR + "산타"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("32");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_포맷() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "양송이수프-1,제로콜라-1,양송이수프-2");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "양송이수프-1,제로콜라-1,");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "양송이수프-1,제로콜라-1-해산물파스타");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트_내용() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1,멸치탕후루-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1,샴페인-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "양송이수프-1,제로콜라-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "양송이수프-10,제로콜라-11");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
