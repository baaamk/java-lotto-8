package lotto.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    private Lotto lotto(List<Integer> lottoNumbers) {
        return Lotto.from(lottoNumbers);
    }

    @Test
    @DisplayName("로또 한 장으로 Lottos가 정상 생성된다")
    void create_with_single_lotto() {
        Lottos lottos = Lottos.of(List.of(
                lotto(List.of(1, 2, 3, 4, 5, 6))
        ));

        assertThat(lottos).isNotNull();
        assertThat(lottos.values()).hasSize(1);
        assertThat(lottos.values().getFirst()).isNotNull();
    }

    @Test
    @DisplayName("여러 장의 로또를 보관한다")
    void hold_multiple_lottos() {
        Lottos lottos = Lottos.of(List.of(
                lotto(List.of(1, 2, 3, 4, 5, 6)),
                lotto(List.of(7, 8, 9, 10, 11, 12)),
                lotto(List.of(13, 14, 15, 16, 17, 18))
        ));

        assertThat(lottos.values()).hasSize(3);
    }

    @Test
    @DisplayName("values()가 반환하는 리스트는 외부에서 수정할 수 없다")
    void values_is_unmodifiable() {
        Lottos lottos = Lottos.of(List.of(
                lotto(List.of(1, 2, 3, 4, 5, 6))
        ));

        assertThatThrownBy(() -> lottos.values().add(lotto(List.of(7, 8, 9, 10, 11, 12))))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("생성 시 전달한 로또와 values()의 요소가 동일하다(참조 동일성 보장 아님)")
    void values_contains_same_elements() {
        Lotto a = lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto b = lotto(List.of(7, 8, 9, 10, 11, 12));
        List<Lotto> input = List.of(a, b);

        Lottos lottos = Lottos.of(input);

        assertThat(lottos.values())
                .hasSize(2)
                .containsExactly(a, b);
    }
}
