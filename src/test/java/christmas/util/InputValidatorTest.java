package christmas.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "32", "0", "100", "a", " "})
    void 유효하지_않은_방문_날짜_검증_예외테스트(String argument) {
        Assertions.assertThatThrownBy(() -> InputValidator.validateVisitDayInput(argument))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_VISIT_DAY_INPUT.getMessage());
    }

}