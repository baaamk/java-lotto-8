package lotto.model.service;

import lotto.controller.dto.LottoResult;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.vo.BonusNumber;
import lotto.model.domain.vo.Money;

public interface LottoService {
    Lottos buy(Money parsedMoney);

    LottoResult matchWith(Lottos lottos, Lotto parsedWinningLotto, BonusNumber parsedBonusNumber);

    double calculateEarningRate(LottoResult result, Money spentMoney);
}
