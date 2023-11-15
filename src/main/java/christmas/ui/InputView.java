package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DetailOrder;
import christmas.util.Converter;

import java.util.List;

import static christmas.util.InputValidator.*;

public class InputView {

    public static List<DetailOrder> readOrders() {
        try {
            OutputView.printReadOrderMenu();
            String input = Console.readLine();

            return Converter.convertInputToDetailOrders(input);
        } catch (IllegalArgumentException e) {
            return readOrders();
        }
    }

    public static int readVisitDay() {
        try {
            OutputView.printReadVisitDay();
            String input = Console.readLine();

            validateVisitDayInput(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            return readVisitDay();
        }
    }
}
