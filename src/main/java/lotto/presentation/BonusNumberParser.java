package lotto.presentation;

import lotto.exception.ErrorMessage;

import java.util.regex.Pattern;

public class BonusNumberParser {
    private static final Pattern NUMBER = Pattern.compile("^[0-9]+$");

    private BonusNumberParser() {
    }

    public static int parse(String inputBonusNumber) {
        if (!NUMBER.matcher(inputBonusNumber).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_INPUT.getMessage());
        }
        return Integer.parseInt(inputBonusNumber);
    }
}
