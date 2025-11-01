package lotto.model.domain.vo;

public class BonusNumber {
    private final LottoNumber bonusNumber;

    public BonusNumber(LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber from(int parsedBonusNumber) {
        return new BonusNumber(LottoNumber.from(parsedBonusNumber));
    }

    public boolean isMatchedWith(LottoNumber lottoNumber) {
        return bonusNumber.equals(lottoNumber);
    }
}
