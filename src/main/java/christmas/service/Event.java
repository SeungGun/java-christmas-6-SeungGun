package christmas.service;

import christmas.domain.Order;

import java.time.LocalDate;

public interface Event {
    int MIN_EVENT_APPLICABLE_AMOUNT = 10000;
    int getBenefitAmount(Order order, LocalDate visitDate);

    default boolean isEventApplicable(Order order){
        return order.getTotalOrderAmount() >= MIN_EVENT_APPLICABLE_AMOUNT;
    }
}
