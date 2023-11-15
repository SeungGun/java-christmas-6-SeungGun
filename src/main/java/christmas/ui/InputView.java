package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DetailOrder;
import christmas.util.Converter;

import java.util.List;

import static christmas.util.InputValidator.*;

public class InputView {

    public static List<DetailOrder> readOrders(String argument) {
        try {
            OutputView.printReadOrderMenu();
            String input = Console.readLine();
            if (argument != null && !argument.isEmpty()) {
                input = argument;
            }
            return Converter.convertInputToDetailOrders(input);
        } catch (IllegalArgumentException e) {
            return readOrders(argument);
        }
    }

    public static int readVisitDay(String argument) {
        try {
            OutputView.printReadVisitDay();
            String input = Console.readLine();
            if (argument != null && !argument.isEmpty()) {
                input = argument;
            }
            validateVisitDayInput(input);
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            return readVisitDay(argument);
        }
    }
}
