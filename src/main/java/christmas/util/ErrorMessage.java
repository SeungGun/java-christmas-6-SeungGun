package christmas.util;

public enum ErrorMessage {
    INVALID_ORDER_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_EXIST_MENU_NAME_IN_MENU_BOARD("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_MENU_NAME_IN_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_EACH_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    CANNOT_ORDER_MENU_IS_ALL_BEVERAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_NAME_AND_COUNT_INPUT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_VISIT_DAY_INPUT("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
