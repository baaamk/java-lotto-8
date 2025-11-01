package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;

import java.util.Objects;

public class LottoNumber {
    private static final int LOTTO_RANGE_START = 1;
    private static final int LOTTO_RANGE_END = 45;

    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int parsedLottoNumber) {
        return new LottoNumber(parsedLottoNumber);
    }

    private void validate(int lottoNumber) {
        if (!isInRange(lottoNumber)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private static boolean isInRange(int lottoNumber) {
        return lottoNumber >= LOTTO_RANGE_START && lottoNumber <= LOTTO_RANGE_END;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumber);
    }
}
