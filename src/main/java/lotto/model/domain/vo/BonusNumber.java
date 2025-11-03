package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;
import lotto.model.domain.Lotto;

import java.util.Objects;

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
