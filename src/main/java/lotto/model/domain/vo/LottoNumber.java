package lotto.model.domain.vo;

import java.util.Objects;

public class LottoNumber {
    private final int lottoNumber;

    private LottoNumber(int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    public static LottoNumber from(int parsedLottoNumber) {
        return new LottoNumber(parsedLottoNumber);
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
