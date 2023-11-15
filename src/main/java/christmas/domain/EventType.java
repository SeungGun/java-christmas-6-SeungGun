package christmas.domain;

public enum EventType {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", true),
    WEEKDAY("평일 할인", true),
    WEEKEND("주말 할인", true),
    SPECIAL("특별 할인", true),
    GIFT("증정 이벤트", false);
    private final String eventName;
    private final boolean isDiscount;

    EventType(String eventName, boolean isDiscount) {
        this.eventName = eventName;
        this.isDiscount = isDiscount;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean isDiscount() {
        return isDiscount;
    }
}
