package lotto.model.strategy;

import lotto.model.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("generate()는 1~45 사이의 6개의 유니크한 번호로 로또를 생성한다")
    void generate_정상생성() {
        LottoGenerator generator = new LottoGenerator();

        Lotto lotto = generator.generate();

        assertThat(lotto.numbers())
                .hasSize(6)
                .doesNotHaveDuplicates()
                .allSatisfy(num ->
                        assertThat(num.getLottoNumber())
                                .isBetween(1, 45)
                );
    }
}
