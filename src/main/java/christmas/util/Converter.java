package christmas.util;

import christmas.domain.DetailOrder;
import christmas.domain.MenuBoard;

import java.util.ArrayList;
import java.util.List;

import static christmas.util.InputValidator.*;

public class Converter {
    private static final String MENU_SPLIT_REGEX = ",";
    private static final String MENU_COUNT_SPLIT_REGEX = "-";

    public static List<DetailOrder> convertInputToDetailOrders(String input) {
        List<DetailOrder> orders = new ArrayList<>();
        String[] menus = splitIntoMenus(removeSpace(input));
        for (String menuAndCount : menus) {
            String[] menuAndCounts = splitIntoMenuAndCount(menuAndCount);

            validateMenuNameAndCountInput(menuAndCounts);
            validateMenuCountIsNumber(menuAndCounts[1]);

            orders.add(new DetailOrder(MenuBoard.findMenuByName(menuAndCounts[0]), Integer.parseInt(menuAndCounts[1])));
        }
        return orders;
    }

    private static String[] splitIntoMenus(String input) {
        return input.split(MENU_SPLIT_REGEX);
    }

    private static String[] splitIntoMenuAndCount(String input) {
        return input.split(MENU_COUNT_SPLIT_REGEX);
    }

    private static String removeSpace(String input) {
        return input.replace(" ", "");
    }
}
