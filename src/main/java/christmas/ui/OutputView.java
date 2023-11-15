package christmas.ui;

import christmas.domain.DetailOrder;
import christmas.domain.EventType;
import christmas.domain.Order;
import christmas.service.Event;
import christmas.util.ErrorMessage;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public static void printErrorMessage(ErrorMessage message) {
        System.out.println("[ERROR] " + message.getMessage());
    }

    public static void printReadOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public static void printReadVisitDay() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
                + "\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public static void printOrderMenus(List<DetailOrder> detailOrders) {
        System.out.println("<주문 메뉴>");
        detailOrders.forEach(detailOrder
                -> System.out.println(detailOrder.getMenu().getName()
                + " " + detailOrder.getCount() + "개"));
    }

    public static void printTotalOrderAmountBeforeDiscount(int amount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(amount));
    }

    public static void printGiftMenu(DetailOrder detailOrder) {
        System.out.println("<증정 메뉴>");
        if (detailOrder == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(detailOrder.getMenu().getName() + " " + detailOrder.getCount() + "개");
    }

    public static void printAllBenefits(Map<EventType, Event> events, Order order, LocalDate visitDate) {
        System.out.println("<혜택 내역>");
        events.forEach((key, value) -> {
            if (value.getBenefitAmount(order, visitDate) > 0) {
                System.out.println(key.getEventName() + ": "
                        + decimalFormat.format(value.getBenefitAmount(order, visitDate) * -1) + "원");
            }
        });
    }

    public static void printTotalBenefit(int totalBenefit) {
        System.out.println("<총혜택 금액>");
        System.out.println(decimalFormat.format(totalBenefit * -1) + "원");
    }


    public static void printPaymentAmountAfterDiscount(int paymentAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(decimalFormat.format(paymentAmount) + "원");
    }
}
