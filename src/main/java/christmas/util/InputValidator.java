package christmas.util;

import static christmas.util.ErrorMessage.*;
import static christmas.util.ExceptionHandler.*;

public class InputValidator {
    private static final int INPUT_MENU_WITH_COUNT_SPLIT_SIZE = 2;
    private static final int MIN_VISIT_DAY = 1;
    private static final int MAX_VISIT_DAY = 31;

    public static void validateMenuNameAndCountInput(String[] input) {
        if (input.length != INPUT_MENU_WITH_COUNT_SPLIT_SIZE) {
            triggerArgException(INVALID_MENU_NAME_AND_COUNT_INPUT);
        }
    }

    public static void validateMenuCountIsNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            triggerArgException(INVALID_MENU_NAME_AND_COUNT_INPUT);
        }
    }

    public static void validateVisitDayInput(String input) {
        try {
            int parseDay = Integer.parseInt(input);
            if (!isDayInRange(parseDay)) {
                triggerArgException(INVALID_VISIT_DAY_INPUT);
            }
        } catch (NumberFormatException e) {
            triggerArgException(INVALID_VISIT_DAY_INPUT);
        }
    }

    private static boolean isDayInRange(int day) {
        return day >= MIN_VISIT_DAY && day <= MAX_VISIT_DAY;
    }
}
