package christmas;

import static christmas.ExceptionHandler.*;

public class InputValidator {
    private static final int INPUT_MENU_WITH_COUNT_SPLIT_SIZE = 2;

    public static void validateMenuNameAndCountInput(String[] input) {
        if (input.length != INPUT_MENU_WITH_COUNT_SPLIT_SIZE) {
            triggerArgException(ErrorMessage.INVALID_MENU_NAME_AND_COUNT_INPUT);
        }
    }

    public static void validateMenuCountIsNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            triggerArgException(ErrorMessage.INVALID_MENU_NAME_AND_COUNT_INPUT);
        }
    }


}
