package christmas;

public enum ErrorMessage {
    INVALID_ORDER_MENU_COUNT("유효하지 않은 주문입니다. 다시 입력해주세요.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
