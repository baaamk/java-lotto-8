package lotto.presentation;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyParserTest {

    @Test
    @DisplayName("정상적인 숫자 문자열을 정수로 변환한다")
    void parse_정상입력() {
        String input = "8000";

        int result = MoneyParser.parse(input);

        assertThat(result).isEqualTo(8000);
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함된 경우 예외 발생")
    void parse_문자포함시_예외() {
        String input = "1000a";

        assertThatThrownBy(() -> MoneyParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
    }

    @Test
    @DisplayName("0 또는 음수 입력 시 예외 발생")
    void parse_0또는음수_예외() {
        String zero = "0";
        String negative = "-1000";

        assertThatThrownBy(() -> MoneyParser.parse(zero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());

        assertThatThrownBy(() -> MoneyParser.parse(negative))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
    }

    @Test
    @DisplayName("공백 또는 빈 문자열 입력 시 예외 발생")
    void parse_공백입력_예외() {
        String input = " ";

        assertThatThrownBy(() -> MoneyParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
    }
}
