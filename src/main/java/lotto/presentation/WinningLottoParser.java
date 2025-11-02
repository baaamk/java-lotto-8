package lotto.presentation;

import lotto.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class WinningLottoParser {
    private static final Pattern DELIMITER_AND_SIZE = Pattern.compile("^(\\d+\\s*,\\s*){5}\\d+$");
    private static final String DELIMITER = ",";

    private WinningLottoParser() {
    }


    public static List<Integer> parse(String inputWinningLottoNumber) {
        if (!DELIMITER_AND_SIZE.matcher(inputWinningLottoNumber).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_INPUT.getMessage());
        }
        return Arrays.stream(inputWinningLottoNumber.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
