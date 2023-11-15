package christmas.domain;

public enum Badge {
    STAR(5000),
    TREE(10000),
    SANTA(20000);
    final int benefitAmount;

    Badge(int benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

    public static Badge getEventBadgeByBenefitAmount(int amount) {
        if (amount >= SANTA.benefitAmount) {
            return SANTA;
        }
        if (amount >= TREE.benefitAmount) {
            return TREE;
        }
        if (amount >= STAR.benefitAmount) {
            return STAR;
        }
        return null;
    }
}
