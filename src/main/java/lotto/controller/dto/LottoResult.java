package lotto.controller.dto;

import lotto.model.domain.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record LottoResult(List<LottoRankResult> results, long totalPrize) {

    private static final Long DEFAULT_VALUE = 0L;

    public static LottoResult from(Map<Rank, Long> rankCounts) {
        return new LottoResult(getLottoRankResults(rankCounts), getTotalPrize(rankCounts));
    }

    private static long getTotalPrize(Map<Rank, Long> rankCounts) {
        return rankCounts.entrySet().stream()
                .mapToLong(
                        entry -> entry.getKey()
                                .calculatePrize(entry.getValue().intValue()))
                .sum();
    }

    private static List<LottoRankResult> getLottoRankResults(Map<Rank, Long> rankCounts) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .map(rank -> new LottoRankResult(
                        rank.getDescription(),
                        rankCounts.getOrDefault(rank, DEFAULT_VALUE),
                        rank.getReward()
                ))
                .toList();
    }
}
