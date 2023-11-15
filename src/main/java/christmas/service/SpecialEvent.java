package christmas.service;

import christmas.domain.Order;

import java.time.LocalDate;
import java.util.List;

public class SpecialEvent implements Event {
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int BENEFIT_AMOUNT = 1000;

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if(!isEventApplicable(order)){
            return 0;
        }
        if (SPECIAL_DAYS.contains(visitDate.getDayOfMonth())) {
            return BENEFIT_AMOUNT;
        }
        return 0;
    }
}
