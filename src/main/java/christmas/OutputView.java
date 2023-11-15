package christmas;

public class OutputView {
    public static void printErrorMessage(ErrorMessage message) {
        System.out.println("[ERROR] " + message.getMessage());
    }

    public static void printReadOrderMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n" +
                "타파스-1,제로콜라-1");
    }
}
