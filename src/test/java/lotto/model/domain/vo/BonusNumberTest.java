package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45, 33})
    @DisplayName("1~45 이내의 숫자가 들어왔을 경우 정상 반환 한다.")
    void _1에서_45_이내의_숫자가_들어오면_정상적으로_BonusNumber를_생성한다(int number) {
        BonusNumber bonusNumber = BonusNumber.from(number);

        Assertions.assertNotNull(bonusNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    @DisplayName("1~45 이외의 숫자가 들어왔을 경우 예외 처리 한다.")
    void _1_에서_45_이외의_숫자가_들어왔을_경우_예외_처리_한다(int parsedLottoNumber) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> BonusNumber.from(parsedLottoNumber))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.INVALID_NUMBER_RANGE.getMessage()));
    }
}