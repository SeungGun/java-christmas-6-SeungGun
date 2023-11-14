package christmas.domain;

import java.util.List;

public class MenuBoard {
    public static final List<Menu> menus = List.of(
            new Menu("양송이수프", Category.APPETIZER, 6000),
            new Menu("타파스", Category.APPETIZER, 5500),
            new Menu("시저샐러드", Category.APPETIZER, 8000),
            new Menu("티본스테이크", Category.MAIN, 55000),
            new Menu("바비큐립", Category.MAIN, 54000),
            new Menu("해산물파스타", Category.MAIN, 35000),
            new Menu("크리스마스파스타", Category.MAIN, 25000),
            new Menu("초코케이크", Category.DESSERT, 15000),
            new Menu("아이스크림", Category.DESSERT, 5000),
            new Menu("제로콜라", Category.BEVERAGE, 3000),
            new Menu("레드와인", Category.BEVERAGE, 60000),
            new Menu("샴페인", Category.BEVERAGE, 25000)
    );

    public static boolean containsName(String name) {
        return menus.stream()
                .anyMatch(menu -> menu.getName().equals(name));
    }
}
