package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DetailOrder;

import java.util.List;

import static christmas.InputValidator.*;

public class InputView {

    public static List<DetailOrder> readOrders() {
        OutputView.printReadOrderMenu();
        String input = Console.readLine();

        return Converter.convertInputToDetailOrders(input);
    }

    public static int readVisitDay() {
        OutputView.printReadVisitDay();
        String input = Console.readLine();

        validateVisitDayInput(input);
        return Integer.parseInt(input);
    }
}
