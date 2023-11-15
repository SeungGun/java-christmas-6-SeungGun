package christmas.service;

import christmas.domain.Category;
import christmas.domain.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class WeekdayEvent implements Event {
    private static final Category BENEFIT_CATEGORY = Category.DESSERT;
    private static final int BENEFIT_AMOUNT = 2023;
    private static final List<DayOfWeek> eventDayOfWeek = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY);

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if (!isEventApplicable(order)) {
            return 0;
        }

        if (eventDayOfWeek.contains(visitDate.getDayOfWeek())) {
            return order.calculateMenuCountByCategory(BENEFIT_CATEGORY) * BENEFIT_AMOUNT;
        }
        return 0;
    }
}
