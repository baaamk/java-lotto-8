package lotto.presentation;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoParserTest {

    @Test
    @DisplayName("쉼표로 구분된 6개의 숫자를 정상적으로 파싱한다")
    void parse_정상입력() {
        String input = "1, 2, 3, 4, 5, 6";

        List<Integer> result = WinningLottoParser.parse(input);

        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("쉼표로 구분된 숫자가 6개가 아닌 경우 예외 발생")
    void parse_숫자개수_잘못된경우() {
        String input = "1,2,3,4,5";

        assertThatThrownBy(() -> WinningLottoParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_WINNING_LOTTO_INPUT.getMessage());
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함된 경우 예외 발생")
    void parse_문자포함시_예외() {
        String input = "1, 2, a, 4, 5, 6";

        assertThatThrownBy(() -> WinningLottoParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_WINNING_LOTTO_INPUT.getMessage());
    }

    @Test
    @DisplayName("쉼표 구분 형식이 잘못된 경우 예외 발생")
    void parse_쉼표형식_잘못된경우() {
        String input = "1 2,3,4,5,6";

        assertThatThrownBy(() -> WinningLottoParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_WINNING_LOTTO_INPUT.getMessage());
    }
}
