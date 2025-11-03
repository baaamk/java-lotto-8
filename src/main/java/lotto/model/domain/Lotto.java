package lotto.model.domain;

import lotto.exception.ErrorMessage;
import lotto.model.domain.vo.LottoNumber;

import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto from(List<Integer> lottoNumbers) {
        return new Lotto(
                lottoNumbers.stream().map(LottoNumber::from).sorted().toList()
        );
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> numbers() {
        return List.copyOf(lottoNumbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
