package christmas.service.event;

import christmas.domain.DetailOrder;
import christmas.domain.Order;
import christmas.service.MenuBoard;

import java.time.LocalDate;

public class GiftEvent implements Event {
    private static final String GIFT_MENU = "샴페인";
    private static final int GIFT_COUNT = 1;
    private static final int MIN_GIFT_CONDITION = 120000;

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if (!isEventApplicable(order)) {
            return 0;
        }

        DetailOrder gift = getGiftMenuWithCount(order);
        if (gift != null) {
            return gift.calculateTotalAmount();
        }
        return 0;
    }

    public DetailOrder getGiftMenuWithCount(Order order) {
        if (order.getTotalOrderAmount() >= MIN_GIFT_CONDITION) {
            return new DetailOrder(MenuBoard.findMenuByName(GIFT_MENU), GIFT_COUNT);
        }
        return null;
    }
}
