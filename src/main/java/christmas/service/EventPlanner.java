package christmas.service;

import christmas.domain.*;
import christmas.ui.InputView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static christmas.ui.OutputView.*;
import static christmas.ui.OutputView.printEventBadge;

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

    public void show() {
        int visitDay = InputView.readVisitDay();
        Order order = new Order(InputView.readOrders());

        LocalDate visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);

        printEventTitleOnDate(visitDay);
        printOrderMenus(order.getDetailOrders());
        printTotalOrderAmountBeforeDiscount(order.getTotalOrderAmount());
        printGiftMenu(((GiftEvent) events.get(EventType.GIFT)).getGiftMenuWithCount(order));
        printAllBenefits(events, order, visitDate);
    }
}
