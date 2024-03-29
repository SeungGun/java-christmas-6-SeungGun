package christmas.service;

import christmas.domain.*;
import christmas.service.event.*;
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
        events.put(EventType.CHRISTMAS_D_DAY, new ChristmasDDayDiscountEvent());
        events.put(EventType.WEEKDAY, new WeekdayDiscountEvent());
        events.put(EventType.WEEKEND, new WeekendDiscountEvent());
        events.put(EventType.GIFT, new GiftEvent());
        events.put(EventType.SPECIAL, new SpecialDiscountEvent());
    }

    public void show() {
        int visitDay = InputView.readVisitDay(null);
        Order order = new Order(InputView.readOrders(null));
        LocalDate visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);

        printEventTitleOnDate(visitDay);
        processBenefit(order, visitDate);
    }

    public void showWithArgs(String[] args) {
        int visitDay = InputView.readVisitDay(args[0]);
        Order order = new Order(InputView.readOrders(args[1]));
        LocalDate visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);

        printEventTitleOnDate(visitDay);
        processBenefit(order, visitDate);
    }

    private void processBenefit(Order order, LocalDate visitDate) {
        int totalOrderAmount = order.getTotalOrderAmount();
        printOrderMenus(order.getDetailOrders());
        printTotalOrderAmountBeforeDiscount(totalOrderAmount);
        printGiftMenu(((GiftEvent) events.get(EventType.GIFT)).getGiftMenuWithCount(order));
        printAllBenefits(events, order, visitDate);
        printTotalBenefit(calculateTotalBenefit(order, visitDate));
        printPaymentAmountAfterDiscount(totalOrderAmount - calculateTotalDiscountAmount(order, visitDate));
        printEventBadge(Badge.getEventBadgeByBenefitAmount(calculateTotalBenefit(order, visitDate)));
    }

    public int calculateTotalBenefit(Order order, LocalDate visitDate) {
        int sum = 0;
        for (EventType eventType : events.keySet()) {
            Event event = events.get(eventType);
            sum += event.getBenefitAmount(order, visitDate);
        }
        return sum;
    }

    public int calculateTotalDiscountAmount(Order order, LocalDate visitDate) {
        int sum = 0;
        for (EventType eventType : events.keySet()) {
            Event event = events.get(eventType);
            if (eventType.isDiscount()) {
                sum += event.getBenefitAmount(order, visitDate);
            }
        }
        return sum;
    }
}
