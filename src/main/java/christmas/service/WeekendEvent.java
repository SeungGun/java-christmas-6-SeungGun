package christmas.service;

import christmas.domain.Category;
import christmas.domain.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekendEvent implements Event {
    private static final Category BENEFIT_CATEGORY = Category.MAIN;
    private final List<DayOfWeek> eventDayOfWeeks = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int BENEFIT_AMOUNT = 2023;

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if (!isEventApplicable(order)) {
            return 0;
        }
        if (eventDayOfWeeks.contains(visitDate.getDayOfWeek())) {
            return order.calculateMenuCountByCategory(BENEFIT_CATEGORY) * BENEFIT_AMOUNT;
        }
        return 0;
    }
}
