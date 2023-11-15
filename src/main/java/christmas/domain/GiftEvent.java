package christmas.domain;

import java.time.LocalDate;

public class GiftEvent implements Event {
    private static final String GIFT_MENU = "샴페인";
    private static final int GIFT_COUNT = 1;

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if (!isEventApplicable(order)) {
            return 0;
        }
        return getGiftMenuWithCount().calculateTotalAmount();
    }

    public DetailOrder getGiftMenuWithCount() {
        return new DetailOrder(MenuBoard.findMenuByName(GIFT_MENU), GIFT_COUNT);
    }
}
