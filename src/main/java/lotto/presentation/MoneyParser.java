package lotto.presentation;

import lotto.exception.ErrorMessage;

import java.util.regex.Pattern;

public class MoneyParser {
    private static final Pattern MONEY_FORMAT = Pattern.compile("^[1-9]\\d*$");

    private MoneyParser() {
    }

    public static int parse(String inputMoney) {
        if (!MONEY_FORMAT.matcher(inputMoney).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
        return Integer.parseInt(inputMoney);
    }

}
