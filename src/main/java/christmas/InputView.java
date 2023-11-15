package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DetailOrder;

import java.util.List;

public class InputView {

    public static List<DetailOrder> readOrders() {
        OutputView.printReadOrderMenu();
        String input = Console.readLine();

        return Converter.convertInputToDetailOrders(input);
    }
}
