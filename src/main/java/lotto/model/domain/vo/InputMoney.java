package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;

public class InputMoney {
    private final int inputMoney;

    public InputMoney(int inputMoney) {
        validate(inputMoney);
        this.inputMoney = inputMoney;
    }

    public static InputMoney from(int inputMoney) {
        return new InputMoney(inputMoney);
    }

    private void validate(int inputMoney) {
        validateMoneyRange(inputMoney);
        validateUnitOfMoney(inputMoney);
    }

    private static void validateUnitOfMoney(int inputMoney) {
        if (inputMoney % 1_000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }

    private static void validateMoneyRange(int inputMoney) {
        if (inputMoney < 1_000) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MIN.getMessage());
        }
        if (inputMoney > 100_000) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MAX.getMessage());
        }
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
