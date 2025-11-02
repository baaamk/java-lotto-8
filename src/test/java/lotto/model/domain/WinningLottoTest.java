package lotto.model.domain;

import lotto.exception.ErrorMessage;
import lotto.model.domain.vo.BonusNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("로또와 보너스 번호가 정상적으로 생성된 경우")
    void 로또와_보너스_번호가_정상_생성된_경우() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = BonusNumber.from(7);

        WinningLotto winningLotto = WinningLotto.from(lotto, bonus);

        assertThat(winningLotto).isNotNull();
    }

    @Test
    @DisplayName("일치 개수와 보너스 번호 일치 여부에 따라 Rank를 반환한다")
    void 일치_개수와_보너스번호에_따라_등수를_반환한다() {
        Lotto winning = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = BonusNumber.from(7);
        WinningLotto winningLotto = WinningLotto.from(winning, bonus);

        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(1, 2, 3, 4, 5, 7));
        Lotto lotto3 = Lotto.from(List.of(1, 2, 3, 4, 5, 9));

        Rank rank1 = winningLotto.calculateRank(lotto1);
        Rank rank2 = winningLotto.calculateRank(lotto2);
        Rank rank3 = winningLotto.calculateRank(lotto3);

        assertThat(rank1).isEqualTo(Rank.FIRST);
        assertThat(rank2).isEqualTo(Rank.SECOND);
        assertThat(rank3).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복될 경우 예외")
    void 당첨번호와_보너스_번호가_중복될_경우_예외() {
        assertThatThrownBy(() -> {
            Lotto winning = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonus = BonusNumber.from(6);
            WinningLotto.from(winning, bonus);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATE_BONUS_NUMBER.getMessage());
    }
}
