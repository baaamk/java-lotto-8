package lotto.model.domain;

import lotto.model.strategy.LottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @Test
    @DisplayName("요청한 개수만큼 로또가 생성된다")
    void publishLottos_정상_개수생성() {
        LottoGenerator generator = new LottoGenerator();
        LottoMachine lottoMachine = new LottoMachine(generator);
        int count = 5;

        Lottos lottos = lottoMachine.publishLottos(count);

        assertThat(lottos.values()).hasSize(count);
    }

    @Test
    @DisplayName("각 로또는 6개의 번호를 가진다")
    void publishLottos_각로또_번호6개() {
        LottoGenerator generator = new LottoGenerator();
        LottoMachine lottoMachine = new LottoMachine(generator);
        int count = 3;

        Lottos lottos = lottoMachine.publishLottos(count);

        lottos.values().forEach(lotto ->
                assertThat(lotto.numbers()).hasSize(6)
        );
    }
}
