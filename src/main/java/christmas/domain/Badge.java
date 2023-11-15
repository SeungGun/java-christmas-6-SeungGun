package christmas.domain;

public enum Badge {
    STAR("별", 5000),
    TREE("나무", 10000),
    SANTA("산타", 20000);
    final String badgeName;
    final int benefitAmount;

    Badge(String badgeName, int benefitAmount) {
        this.badgeName = badgeName;
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

    public int getBenefitAmount() {
        return benefitAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }
}
