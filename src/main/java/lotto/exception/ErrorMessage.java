package lotto.exception;

public enum ErrorMessage {
    INVALID_NUMBER_RANGE("1~45이내의 숫자를 입력해 주세요."),
    DUPLICATE_BONUS_NUMBER("당첨번호와 중복된 숫자를 입력하지 마세요."),
    INVALID_MONEY_MIN("최소 입력 금액은 1,000원 입니다."),
    INVALID_MONEY_MAX("1회 최대 입력 금액은 100,000원 입니다."),
    INVALID_MONEY_UNIT("1,000원 단위로 입력해 주세요."),
    INVALID_LOTTO_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("중복된 당첨번호가 있습니다."),
    INVALID_NUMBER_INPUT("0 이상의 숫자만 입력해 주세요."),
    INVALID_WINNING_LOTTO_INPUT("쉼표로 구분된 6개의 숫자를 입력해 주세요.");

    private final String message;
    private static final String ERROR_MESSAGE = "[ERROR] ";


    ErrorMessage(String message) {
        this.message = ERROR_MESSAGE + message;
    }

    public String getMessage() {
        return message;
    }
}
