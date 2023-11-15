package christmas.service;

import christmas.domain.*;

import java.util.HashMap;
import java.util.Map;

public class EventPlanner {
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private final Map<EventType, Event> events = new HashMap<>();

    public EventPlanner() {
        events.put(EventType.CHRISTMAS_D_DAY, new ChristmasDDayEvent());
        events.put(EventType.WEEKDAY, new WeekdayEvent());
        events.put(EventType.WEEKEND, new WeekendEvent());
        events.put(EventType.GIFT, new GiftEvent());
        events.put(EventType.SPECIAL, new SpecialEvent());
    }
}
