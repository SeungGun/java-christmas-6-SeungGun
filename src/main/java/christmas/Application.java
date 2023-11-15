package christmas;

import christmas.service.EventPlanner;

public class Application {
    public static void main(String[] args) {
        if (args.length > 0) {
            new EventPlanner().showWithArgs(args);
            return;
        }
        new EventPlanner().show();
    }
}
