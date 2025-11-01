package lotto.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {
    @Test
    @DisplayName("로또와 보너스 번호가 정상 생성된 경우")
    void 로또와_보너스_번호가_정상_생성된_경우() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.from(lotto);

        Assertions.assertThat(winningLotto)
                .usingRecursiveComparison()
                .isEqualTo(WinningLotto.from(lotto));
    }
}