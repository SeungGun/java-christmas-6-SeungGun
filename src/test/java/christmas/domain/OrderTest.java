package christmas.domain;

import christmas.service.MenuBoard;
import christmas.util.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


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
        assertThatThrownBy(() -> new Order(detailOrders1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_MENU_COUNT.getMessage());

        assertThatThrownBy(() -> new Order(detailOrders2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_MENU_COUNT.getMessage());
    }

    @Test
    void 메뉴판에_없는_메뉴_예외테스트() {
        // given
        String menuName1 = "abcd";
        String menuName2 = "없는메뉴";

        List<DetailOrder> detailOrders2 = List.of(
                new DetailOrder(
                        new Menu(menuName1, Category.MAIN, 5000),
                        2),
                new DetailOrder(
                        new Menu(menuName2, Category.MAIN, 5000),
                        2));

        // when, then
        assertThatThrownBy(() -> new Order(detailOrders2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXIST_MENU_NAME_IN_MENU_BOARD.getMessage());
    }

    @Test
    void 중복되는_메뉴이름_예외테스트() {
        // given
        String name = "아이스크림";
        Menu menu = MenuBoard.findMenuByName(name);

        List<DetailOrder> detailOrders = List.of(
                new DetailOrder(menu, 2),
                new DetailOrder(menu, 3));

        // when, then
        assertThatThrownBy(() -> new Order(detailOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_MENU_NAME_IN_ORDER.getMessage());
    }

    @Test
    void 유효하지_않은_각_메뉴_개수_예외테스트() {
        // given
        String name = "아이스크림";
        String name2 = "초코케이크";
        Menu menu = MenuBoard.findMenuByName(name);
        Menu menu2 = MenuBoard.findMenuByName(name2);

        List<DetailOrder> detailOrders = List.of(
                new DetailOrder(menu, 0),
                new DetailOrder(menu2, -1));

        // when, then
        assertThatThrownBy(() -> new Order(detailOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_EACH_MENU_COUNT.getMessage());
    }

    @Test
    void 음료만_주문_하는_예외테스트() {
        // given
        String name = "제로콜라";
        String name2 = "레드와인";

        Menu menu = MenuBoard.findMenuByName(name);
        Menu menu2 = MenuBoard.findMenuByName(name2);

        List<DetailOrder> detailOrders = List.of(
                new DetailOrder(menu, 3),
                new DetailOrder(menu2, 1));

        // when, then
        assertThatThrownBy(() -> new Order(detailOrders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CANNOT_ORDER_MENU_IS_ALL_BEVERAGE.getMessage());
    }

    @Test
    void 할인_전_총_주문_금액_테스트() {
        // given
        String name = "티본스테이크";
        String name2 = "레드와인";

        Menu menu = MenuBoard.findMenuByName(name);
        Menu menu2 = MenuBoard.findMenuByName(name2);

        MenuBoard.findMenuByName(name2);
        Order order = new Order(List.of(
                new DetailOrder(menu, 1),
                new DetailOrder(menu2, 2)));

        assertThat(order.getTotalOrderAmount()).
                isEqualTo(175000);
    }

    @Test
    void 특정_카테고리에_해당하는_메뉴의_총개수_테스트() {
        // given
        Menu menu1 = MenuBoard.findMenuByName("타파스");
        Menu menu2 = MenuBoard.findMenuByName("바비큐립");
        Menu menu3 = MenuBoard.findMenuByName("해산물파스타");
        Menu menu4 = MenuBoard.findMenuByName("초코케이크");
        Menu menu5 = MenuBoard.findMenuByName("제로콜라");

        Category testCategory1 = Category.MAIN;
        Category testCategory2 = Category.DESSERT;

        Order order = new Order(List.of(
                new DetailOrder(menu1, 2),
                new DetailOrder(menu2, 3),
                new DetailOrder(menu3, 2),
                new DetailOrder(menu4, 2),
                new DetailOrder(menu5, 1)));

        assertThat(order.calculateMenuCountByCategory(testCategory1))
                .isEqualTo(5);
        assertThat(order.calculateMenuCountByCategory(testCategory2))
                .isEqualTo(2);
    }
}