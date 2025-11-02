package lotto.presentation;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberParserTest {

    @Test
    @DisplayName("숫자 문자열을 정수로 정상 변환한다")
    void parse_정상입력() {
        String input = "7";

        int result = BonusNumberParser.parse(input);

        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함된 경우 예외 발생")
    void parse_비정상입력_예외() {
        String input = "a5";

        assertThatThrownBy(() -> BonusNumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
    }

    @Test
    @DisplayName("공백 문자열 입력 시 예외 발생")
    void parse_공백입력_예외() {
        String input = " ";

        assertThatThrownBy(() -> BonusNumberParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
    }
}
