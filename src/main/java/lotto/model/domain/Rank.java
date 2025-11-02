package lotto.model.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치"),
    NONE(0, false, 0, "0개 일치");

    private final int matchCount;
    private final boolean requiresBonus;
    private final int reward;
    private final String description;

    Rank(int matchCount, boolean requiresBonus, int reward, String description) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.reward = reward;
        this.description = description;
    }

    public static Rank of(int matchCount, boolean bonusMatched) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && bonusMatched) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }

    public int calculatePrize(int count) {
        return reward * count;
    }

    public int getReward() {
        return reward;
    }

    public String getDescription() {
        return description;
    }
}

