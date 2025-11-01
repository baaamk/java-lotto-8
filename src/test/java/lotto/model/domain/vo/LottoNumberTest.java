package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45, 33})
    @DisplayName("1~45 이내의 숫자가 들어왔을 경우 정상 반환 한다.")
    void _1_에서_45_이내의_숫자가_들어왔을_경우_정상_반환_한다(int parsedLottoNumber) {
        LottoNumber lottoNumber = LottoNumber.from(parsedLottoNumber);
        Assertions.assertEquals(LottoNumber.from(parsedLottoNumber), lottoNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    @DisplayName("1~45 이외의 숫자가 들어왔을 경우 예외 처리 한다.")
    void _1_에서_45_이외의_숫자가_들어왔을_경우_예외_처리_한다(int parsedLottoNumber) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> LottoNumber.from(parsedLottoNumber))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining(ErrorMessage.INVALID_NUMBER_RANGE.getMessage()));
    }

    @Test
    @DisplayName("다른 숫자와 비교해서 중복이 있으면 ture를 반환한다.")
    void 다른_숫자와_비교해서_중복이_있다면_ture를_반환한다() {
        LottoNumber lottoNumber = LottoNumber.from(1);
        LottoNumber lottoNumber2 = LottoNumber.from(1);
        Assertions.assertTrue(lottoNumber2.isDuplicate(lottoNumber));
    }
}