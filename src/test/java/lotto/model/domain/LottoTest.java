package lotto.model.domain;

import lotto.exception.ErrorMessage;
import lotto.model.domain.vo.LottoNumber;
import org.assertj.core.api.InstanceOfAssertFactories;
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
    @DisplayName("로또 생성지 번호가 정렬된 상태로 저장된다")
    void 로또_생성시_번호가_정렬된_상태로_저장된다() {
        List<Integer> unordered = List.of(44, 1, 30, 7, 25, 12);

        Lotto lotto = Lotto.from(unordered);

        assertThat(lotto.numbers())
                .asInstanceOf(InstanceOfAssertFactories.list(LottoNumber.class))
                .extracting(LottoNumber::getLottoNumber)
                .containsExactly(1, 7, 12, 25, 30, 44);

    }

    @Test
    @DisplayName("로또 생성 실패: null이 들어오면 NullPointerException 발생")
    void createLotto_fail_null() {
        assertThatThrownBy(() -> Lotto.from(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(ErrorMessage.NULL_EXCEPTION.getMessage());
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
