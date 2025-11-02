package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("1000원 단위의 숫자가 들어올 경우 정상 반환한다.")
    void _1000원_단위의_숫자가_들어올_경우_정상_반환한다() {
        Money inputMoney = Money.from(1_000);
        Assertions.assertEquals(1_000, inputMoney.getInputMoney());
    }

    @Test
    @DisplayName("1000원 미만의 숫자가 들어올 경우 예외처리")
    void _1000_원_미만의_숫자가_들어올_경우_예외처리() {
        assertThatThrownBy(() -> Money.from(900))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MONEY_MIN.getMessage());
    }

    @Test
    @DisplayName("100,000원 초과의 숫자가 들어올 경우 예외처리")
    void _100_000_원_초과의_숫자가_들어올_경우_예외처리() {
        assertThatThrownBy(() -> Money.from(101_300))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MONEY_MAX.getMessage());
    }

    @Test
    @DisplayName("1,000원 단위가 아닌 숫자가 들어올 경우 예외처리")
    void _1_000_원_단위가_아닌_숫자가_들어올_경우_예외처리() {
        assertThatThrownBy(() -> Money.from(9_500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
    }
}