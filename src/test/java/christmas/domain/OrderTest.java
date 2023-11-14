package christmas.domain;

import christmas.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class OrderTest {

    @Test
    void 주문_메뉴의_총_개수가_유효하지않은_예외테스트() {
        // given
        List<DetailOrder> detailOrders1 = new ArrayList<>(); // 0개
        List<DetailOrder> detailOrders2 = List.of(
                new DetailOrder(
                        new Menu("테스트 메뉴", Category.MAIN, 5000),
                        21)); // 21개

        // when, then
        Assertions.assertThatThrownBy(() -> new Order(detailOrders1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_MENU_COUNT.getMessage());

        Assertions.assertThatThrownBy(() -> new Order(detailOrders2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_MENU_COUNT.getMessage());
    }
}