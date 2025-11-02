package lotto.model.domain;


import lotto.exception.ErrorMessage;
import lotto.model.domain.vo.BonusNumber;

public class WinningLotto {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    private WinningLotto(Lotto winningLotto, BonusNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto from(Lotto winningLotto, BonusNumber bonusNumber) {
        validate(winningLotto, bonusNumber);
        return new WinningLotto(winningLotto, bonusNumber);
    }

    private static void validate(Lotto winningLotto, BonusNumber bonusNumber) {
        if (bonusNumber.isMatchedWith(winningLotto)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    public Rank calculateRank(Lotto lotto) {
        int matchCount = (int) lotto.numbers().stream()
                .filter(winningLotto::contains)
                .count();

        boolean bonusMatched = bonusNumber.isMatchedWith(lotto);
        return Rank.of(matchCount, bonusMatched);
    }
}
