package lotto.model.service;

import lotto.controller.dto.LottoResult;
import lotto.exception.ErrorMessage;
import lotto.model.domain.*;
import lotto.model.domain.vo.BonusNumber;
import lotto.model.domain.vo.Money;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoServiceImpl implements LottoService{
    private static final int PERCENT_UNIT = 100;
    private static final double ROUND_UNIT = 100.0;

    private final LottoMachine lottoMachine;

    public LottoServiceImpl(LottoMachine lottoMachine) {
        Objects.requireNonNull(lottoMachine, ErrorMessage.NULL_EXCEPTION.getMessage());
        this.lottoMachine = lottoMachine;
    }

    public Lottos buy(Money money) {
        Objects.requireNonNull(money, ErrorMessage.NULL_EXCEPTION.getMessage());
        return lottoMachine.publishLottos(money.returnAmount());
    }

    public LottoResult matchWith(Lottos lottos, Lotto winningLotto , BonusNumber bonusNumber) {
        WinningLotto winning =
                WinningLotto.from(winningLotto, bonusNumber);

        List<Rank> ranks = lottos.values().stream()
                .map(winning::calculateRank)
                .toList();

        Map<Rank, Long> rankCounts = ranks.stream()
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        return LottoResult.from(rankCounts);
    }

    public double calculateEarningRate(LottoResult result, Money spentMoney) {
        double rate = (double) result.totalPrize() / spentMoney.getInputMoney() * PERCENT_UNIT;

        return Math.round(rate * PERCENT_UNIT) / ROUND_UNIT;
    }
}
