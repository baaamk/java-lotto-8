package lotto.controller.converter;

import lotto.controller.dto.LottoPurchaseResult;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoDtoConverterTest {

    @Test
    @DisplayName("Lottos 객체를 LottoPurchaseResult DTO로 변환한다.")
    void Lottos_객체를_LottoPurchaseResult_DTO로_변환한다() {
        Lotto lotto1 = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.from(List.of(7, 8, 9, 10, 11, 12));
        Lottos lottos = Lottos.of(List.of(lotto1, lotto2));

        LottoPurchaseResult dto = LottoDtoConverter.toDto(lottos);

        assertThat(dto.lottoNumbers())
                .containsExactly(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(7, 8, 9, 10, 11, 12)
                );
    }
}
