package lotto.controller.converter;

import lotto.controller.dto.LottoPurchaseResult;
import lotto.model.domain.Lottos;
import lotto.model.domain.vo.LottoNumber;

import java.util.List;

public final class LottoDtoConverter {
    private LottoDtoConverter() {}

    public static LottoPurchaseResult toDto(Lottos lottos) {
        List<List<Integer>> numbers = lottos.values().stream()
                .map(lotto -> lotto.numbers().stream()
                        .map(LottoNumber::getLottoNumber)
                        .toList())
                .toList();

        return LottoPurchaseResult.from(numbers);
    }
}

