package lotto.model.service;

import lotto.controller.dto.LottoResult;
import lotto.model.domain.*;
import lotto.model.domain.vo.BonusNumber;
import lotto.model.domain.vo.Money;
import lotto.model.strategy.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceImplTest {

    @Test
    @DisplayName("금액에 맞게 로또가 구매된다")
    void buy_정상_생성() {
        LottoMachine lottoMachine = new LottoMachine(new LottoGenerator());
        LottoServiceImpl service = new LottoServiceImpl(lottoMachine);
        Money money = Money.from(8000);

        Lottos lottos = service.buy(money);

        assertThat(lottos.values()).hasSize(8);
    }

    @Test
    @DisplayName("로또 결과를 정확히 집계한다 (5등 1개)")
    void matchWith_정상_집계() {
        LottoMachine lottoMachine = new LottoMachine(new LottoGenerator());
        LottoServiceImpl service = new LottoServiceImpl(lottoMachine);

        Lottos lottos = Lottos.of(List.of(
                Lotto.from(List.of(1, 2, 3, 10, 20, 30)),
                Lotto.from(List.of(7, 8, 9, 13, 14, 15))
        ));

        Lotto winningNumbers = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = BonusNumber.from(7);

        LottoResult result = service.matchWith(lottos, winningNumbers, bonusNumber);

        assertThat(result.totalPrize()).isEqualTo(5_000);
    }

    @Test
    @DisplayName("calculateEarningRate(): 수익률 계산이 올바르다")
    void calculateEarningRate_정상() {
        LottoMachine lottoMachine = new LottoMachine(new LottoGenerator());
        LottoServiceImpl service = new LottoServiceImpl(lottoMachine);

        LottoResult result = new LottoResult(List.of(), 5_000);
        Money spentMoney = Money.from(8_000);

        double rate = service.calculateEarningRate(result, spentMoney);

        assertThat(rate).isEqualTo(62.5);
    }
}
