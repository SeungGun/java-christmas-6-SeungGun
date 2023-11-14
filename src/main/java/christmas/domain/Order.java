package christmas.domain;

import java.util.List;

import static christmas.ErrorMessage.*;
import static christmas.ExceptionHandler.*;

public class Order {
    private static final int LIMIT_TOTAL_MENU_COUNT = 20;

    private final List<DetailOrder> detailOrders;

    public Order(List<DetailOrder> detailOrders) {
        validateTotalMenuCount(detailOrders);
        validateMenuNameIncludedInMenuBoard(detailOrders);
        validateDuplicateMenuName(detailOrders);
        validateEachMenuCount(detailOrders);
        validateOnlyBeverageMenu(detailOrders);
        this.detailOrders = detailOrders;
    }

    private void validateTotalMenuCount(List<DetailOrder> detailOrders) {
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

    private void validateDuplicateMenuName(List<DetailOrder> detailOrders) {
        if (calculateUniqueMenuNameCount(detailOrders) != detailOrders.size()) {
            triggerArgException(DUPLICATE_MENU_NAME_IN_ORDER);
        }
    }

    private void validateEachMenuCount(List<DetailOrder> detailOrders) {
        if (isNotPositiveMenuCount(detailOrders)) {
            triggerArgException(INVALID_EACH_MENU_COUNT);
        }
    }

    private void validateOnlyBeverageMenu(List<DetailOrder> detailOrders) {
        if (isAllBeverageMenu(detailOrders)) {
            triggerArgException(CANNOT_ORDER_MENU_IS_ALL_BEVERAGE);
        }
    }

    private int calculateTotalMenuCount(List<DetailOrder> detailOrders) {
        return detailOrders.stream()
                .mapToInt(DetailOrder::getCount)
                .sum();
    }

    private int calculateUniqueMenuNameCount(List<DetailOrder> detailOrders) {
        return (int) detailOrders.stream()
                .map(detail -> detail.getMenu().getName())
                .distinct()
                .count();
    }

    private boolean isNotPositiveMenuCount(List<DetailOrder> detailOrders) {
        return detailOrders.stream()
                .anyMatch(detailOrder -> detailOrder.getCount() < 1);
    }

    private boolean isAllBeverageMenu(List<DetailOrder> detailOrders) {
        return detailOrders.stream()
                .filter(detailOrder ->
                        detailOrder.getMenu().getCategory() == Category.BEVERAGE)
                .count() == detailOrders.size();
    }
}

