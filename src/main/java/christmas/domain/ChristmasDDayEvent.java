package christmas.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDDayEvent implements Event {
    private static final int START_AMOUNT = 1000;
    private static final int INCREASING_AMOUNT = 100;
    private static final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private static final LocalDate endDate = LocalDate.of(2023, 12, 25);

    @Override
    public int getBenefitAmount(Order order, LocalDate visitDate) {
        if (!isEventApplicable(order)) {
            return 0;
        }

        if (visitDate.getDayOfMonth() > endDate.getDayOfMonth()) {
            return 0;
        }

        long diffDay = ChronoUnit.DAYS.between(startDate, visitDate);
        return ((int) diffDay * INCREASING_AMOUNT) + START_AMOUNT;
    }


}
