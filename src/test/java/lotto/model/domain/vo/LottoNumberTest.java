package lotto.model.domain.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45, 33})
    @DisplayName("1~45 이내의 숫자가 들어왔을 경우 정상 반환 한다.")
    void _1_에서_45_이내의_숫자가_들어왔을_경우_정상_반환_한다(int parsedLottoNumber) {
        LottoNumber lottoNumber = LottoNumber.from(parsedLottoNumber);
        Assertions.assertEquals(LottoNumber.from(parsedLottoNumber), lottoNumber);
    }
}