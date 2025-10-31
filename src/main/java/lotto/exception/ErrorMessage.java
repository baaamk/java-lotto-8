package lotto.exception;

public enum ErrorMessage {
    INVALID_NUMBER_RANGE("1~45이내의 숫자를 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
