package christmas.service;

import christmas.util.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MenuBoardTest {

    @ParameterizedTest
    @ValueSource(strings = {"딸기케이크", "코카콜라", "바비큐"})
    void 메뉴이름이_메뉴판에_포함되는지_테스트(String argument) {
        assertThat(MenuBoard.containsName(argument)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"봉골레파스타", "칵테일", "치킨샐러드", "물"})
    void 메뉴판에_없는_메뉴이름_예외테스트(String argument) {
        assertThatThrownBy(() -> MenuBoard.findMenuByName(argument))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_EXIST_MENU_NAME_IN_MENU_BOARD.getMessage());
    }
}