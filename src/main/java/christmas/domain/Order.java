package christmas.domain;

import java.util.List;

import static christmas.ErrorMessage.*;
import static christmas.ExceptionHandler.*;

public class Order {
    private static final int LIMIT_TOTAL_MENU_COUNT = 20;

    private final List<DetailOrder> detailOrders;

    public Order(List<DetailOrder> detailOrders) {
        validateMenuCount(detailOrders);
        this.detailOrders = detailOrders;
    }

    private void validateMenuCount(List<DetailOrder> detailOrders) {
        if (detailOrders.isEmpty()
                || calculateTotalMenuCount(detailOrders) > LIMIT_TOTAL_MENU_COUNT) {
            triggerArgException(INVALID_ORDER_MENU_COUNT);
        }
    }

    private int calculateTotalMenuCount(List<DetailOrder> detailOrders) {
        return detailOrders.stream()
                .mapToInt(DetailOrder::getCount)
                .sum();
    }
}

