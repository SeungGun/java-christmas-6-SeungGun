package christmas.domain;

import java.util.List;

public class Order {
    private final List<DetailOrder> detailOrders;

    public Order(List<DetailOrder> detailOrders) {
        this.detailOrders = detailOrders;
    }
}

