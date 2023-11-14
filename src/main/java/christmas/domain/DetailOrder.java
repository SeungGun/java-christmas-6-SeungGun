package christmas.domain;

public class DetailOrder {
    private Menu menu;
    private int count;

    public DetailOrder(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
