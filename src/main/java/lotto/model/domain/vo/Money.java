package lotto.model.domain.vo;

import lotto.exception.ErrorMessage;

public class Money {
    private static final int THOUSAND_UNIT = 1_000;
    private static final int ZERO = 0;
    private static final int MAX_MONEY_INPUT_AMOUNT = 100_000;

    private final int inputMoney;

    public Money(int inputMoney) {
        validate(inputMoney);
        this.inputMoney = inputMoney;
    }

    public static Money from(int inputMoney) {
        return new Money(inputMoney);
    }

    public int returnAmount() {
        return inputMoney / THOUSAND_UNIT;
    }

    private void validate(int inputMoney) {
        validateMoneyRange(inputMoney);
        validateUnitOfMoney(inputMoney);
    }

    private static void validateUnitOfMoney(int inputMoney) {
        if (inputMoney % THOUSAND_UNIT != ZERO) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }

    private static void validateMoneyRange(int inputMoney) {
        if (inputMoney < THOUSAND_UNIT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MIN.getMessage());
        }
        if (inputMoney > MAX_MONEY_INPUT_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_MAX.getMessage());
        }
    }

    public int getInputMoney() {
        return inputMoney;
    }
}
