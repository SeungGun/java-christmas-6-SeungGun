package christmas.service;

import christmas.domain.Badge;
import christmas.domain.DetailOrder;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.service.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EventTest {
    private Order order;
    private LocalDate visitDate;
    private Event event;

    @BeforeEach
    void init() {
        Menu menu1 = MenuBoard.findMenuByName("타파스");
        Menu menu2 = MenuBoard.findMenuByName("티본스테이크");
        Menu menu3 = MenuBoard.findMenuByName("아이스크림");
        Menu menu4 = MenuBoard.findMenuByName("제로콜라");
        Menu menu5 = MenuBoard.findMenuByName("바비큐립");
        order = new Order(List.of(
                new DetailOrder(menu1, 2),
                new DetailOrder(menu2, 3),
                new DetailOrder(menu3, 2),
                new DetailOrder(menu4, 4),
                new DetailOrder(menu5, 1)));
    }

    @Test
    void 평일_할인_이벤트_혜택_금액_테스트() {
        // given
        event = new WeekdayDiscountEvent();
        visitDate = LocalDate.of(2023, 12, 5);
        LocalDate visitDate2 = LocalDate.of(2023, 12, 8);

        // when
        int benefitAmount = event.getBenefitAmount(order, visitDate);
        int benefitAmount2 = event.getBenefitAmount(order, visitDate2);

        // then
        assertThat(benefitAmount).isEqualTo(4046);
        assertThat(benefitAmount2).isEqualTo(0);
    }

    @Test
    void 주말_할인_이벤트_혜택_금액_테스트() {
        // given
        event = new WeekendDiscountEvent();
        visitDate = LocalDate.of(2023, 12, 5);
        LocalDate visitDate2 = LocalDate.of(2023, 12, 8);

        // when
        int benefitAmount = event.getBenefitAmount(order, visitDate);
        int benefitAmount2 = event.getBenefitAmount(order, visitDate2);

        // then
        assertThat(benefitAmount).isEqualTo(0);
        assertThat(benefitAmount2).isEqualTo(8092);
    }

    @Test
    void 크리스마스_디데이_할인_혜택_금액_테스트() {
        // given
        event = new ChristmasDDayDiscountEvent();
        visitDate = LocalDate.of(2023, 12, 5);
        LocalDate visitDate2 = LocalDate.of(2023, 12, 27);

        // when
        int benefitAmount = event.getBenefitAmount(order, visitDate);
        int benefitAmount2 = event.getBenefitAmount(order, visitDate2);

        // then
        assertThat(benefitAmount).isEqualTo(1400);
        assertThat(benefitAmount2).isEqualTo(0);
    }

    @Test
    void 특별_할인_혜택_금액_테스트() {
        // given
        event = new SpecialDiscountEvent();
        visitDate = LocalDate.of(2023, 12, 3);
        LocalDate visitDate2 = LocalDate.of(2023, 12, 4);

        // when
        int benefitAmount = event.getBenefitAmount(order, visitDate);
        int benefitAmount2 = event.getBenefitAmount(order, visitDate2);

        // then
        assertThat(benefitAmount).isEqualTo(1000);
        assertThat(benefitAmount2).isEqualTo(0);
    }

    @Test
    void 증정_이벤트_혜택_내역_테스트() {
        // given
        event = new GiftEvent();
        visitDate = LocalDate.of(2023, 12, 3);

        // when
        int benefitAmount = event.getBenefitAmount(order, visitDate);
        DetailOrder giftMenuWithCount = ((GiftEvent) event).getGiftMenuWithCount(order);

        // then
        assertThat(benefitAmount).isEqualTo(25000);
        assertThat(giftMenuWithCount.getCount()).isEqualTo(1);
        assertThat(giftMenuWithCount.getMenu().getName()).isEqualTo("샴페인");
    }

    @Test
    void 총_혜택_금액_계산_테스트() {
        // given
        visitDate = LocalDate.of(2023, 12, 3);
        EventPlanner planner = new EventPlanner();

        // when
        int totalBenefit = planner.calculateTotalBenefit(order, visitDate);

        // then
        assertThat(totalBenefit).isEqualTo(31246);
    }

    @Test
    void 총_할인_금액_계산_테스트() {
        // given
        visitDate = LocalDate.of(2023, 12, 3);
        EventPlanner planner = new EventPlanner();

        // when
        int discountAmount = planner.calculateTotalDiscountAmount(order, visitDate);

        // then
        assertThat(discountAmount).isEqualTo(6246);
    }

    @Test
    void 혜택_금액에_따른_이벤트_배지_테스트() {
        // given
        visitDate = LocalDate.of(2023, 12, 3);
        EventPlanner planner = new EventPlanner();

        // when
        int totalBenefit = planner.calculateTotalBenefit(order, visitDate);
        Badge badge = Badge.getEventBadgeByBenefitAmount(totalBenefit);
        Badge badge2 = Badge.getEventBadgeByBenefitAmount(3000);

        // then
        assertThat(badge).isEqualTo(Badge.SANTA);
        assertThat(badge2).isEqualTo(Badge.NONE);
    }
}
