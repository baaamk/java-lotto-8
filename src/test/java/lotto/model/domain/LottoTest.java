package lotto.model.domain;

import lotto.exception.ErrorMessage;
import lotto.model.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {

    @Test
    @DisplayName("로또 번호가 정상적으로 들어오면 정상 반환 한다.")
    void 로또_번호가_정상적으로_들어오면_정상_반환_한다() {
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 4, 5, 6));

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> actual = lotto.getLottoNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .toList();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_LOTTO_SIZE.getMessage());
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.DUPLICATE_LOTTO_NUMBER.getMessage());
    }
}
