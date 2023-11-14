package christmas.domain;

import java.util.List;

import static christmas.ErrorMessage.*;
import static christmas.ExceptionHandler.*;

public class Order {
    private static final int LIMIT_TOTAL_MENU_COUNT = 20;

    private final List<DetailOrder> detailOrders;

    public Order(List<DetailOrder> detailOrders) {
        validateMenuCount(detailOrders);
        validateMenuNameIncludedInMenuBoard(detailOrders);
        this.detailOrders = detailOrders;
    }

    private void validateMenuCount(List<DetailOrder> detailOrders) {
        if (detailOrders.isEmpty()
                || calculateTotalMenuCount(detailOrders) > LIMIT_TOTAL_MENU_COUNT) {
            triggerArgException(INVALID_ORDER_MENU_COUNT);
        }
    }

    private void validateMenuNameIncludedInMenuBoard(List<DetailOrder> detailOrders) {
        detailOrders.stream()
                .filter(detailOrder -> !MenuBoard.containsName(detailOrder.getMenu().getName()))
                .findFirst()
                .ifPresent(detailOrder -> triggerArgException(NOT_EXIST_MENU_NAME_IN_MENU_BOARD));
    }

    private int calculateTotalMenuCount(List<DetailOrder> detailOrders) {
        return detailOrders.stream()
                .mapToInt(DetailOrder::getCount)
                .sum();
    }
}

