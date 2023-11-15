package christmas.util;

import christmas.domain.DetailOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {"타파스2,시저샐러드3", "샴페인-한개,제로콜라-두개", "바비큐립, 초코케이크"})
    void 유효하지_않은_주문_입력_예외테스트(String argument) {
        assertThatThrownBy(() -> Converter.convertInputToDetailOrders(argument))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MENU_NAME_AND_COUNT_INPUT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"아이스크림-1,레드와인-3"})
    void 주문_입력_변환_테스트(String argument) {
        List<DetailOrder> detailOrders = Converter.convertInputToDetailOrders(argument);
        assertThat(detailOrders.size()).isEqualTo(2);
        assertThat(detailOrders.get(0).getCount()).isEqualTo(1);
        assertThat(detailOrders.get(1).getCount()).isEqualTo(3);
    }
}