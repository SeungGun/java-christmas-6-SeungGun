package christmas.domain;

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

        if (order.getTotalOrderAmount() >= MIN_GIFT_CONDITION) {
            return getGiftMenuWithCount().calculateTotalAmount();
        }
        return 0;
    }

    public DetailOrder getGiftMenuWithCount() {
        return new DetailOrder(MenuBoard.findMenuByName(GIFT_MENU), GIFT_COUNT);
    }
}
