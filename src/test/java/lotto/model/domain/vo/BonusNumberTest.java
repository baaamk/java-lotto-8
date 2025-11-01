package lotto.model.domain.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 45, 33})
    @DisplayName("1~45 이내의 숫자가 들어왔을 경우 정상 반환 한다.")
    void _1에서_45_이내의_숫자가_들어오면_정상적으로_BonusNumber를_생성한다(int number) {
        BonusNumber bonusNumber = BonusNumber.from(number);

        assertTrue(bonusNumber.isMatchedWith(LottoNumber.from(number)));
    }

}