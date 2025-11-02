package lotto.controller.dto;

import java.util.List;

public record LottoPurchaseResult(int count, List<List<Integer>> lottoNumbers) {

    public static LottoPurchaseResult from(List<List<Integer>> lottoNumbers) {
        return new LottoPurchaseResult(lottoNumbers.size(), lottoNumbers);
    }
}
