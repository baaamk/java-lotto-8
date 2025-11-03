package lotto.model.domain.vo;

import lotto.model.domain.Lotto;

public class BonusNumber {
    private final LottoNumber bonusNumber;

    public BonusNumber(LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber from(int parsedBonusNumber) {
        return new BonusNumber(LottoNumber.from(parsedBonusNumber));
    }

    public boolean isMatchedWith(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
