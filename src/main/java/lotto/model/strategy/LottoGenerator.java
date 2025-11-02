package lotto.model.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.domain.Lotto;

public class LottoGenerator implements Generator{

    private static final int NUMBER_RANGE_MIN = 1;
    private static final int NUMBER_RANGE_MAX = 45;
    private static final int NUMBERS_SIZE = 6;

    @Override
    public Lotto generate() {
        return Lotto.from(Randoms.pickUniqueNumbersInRange(NUMBER_RANGE_MIN, NUMBER_RANGE_MAX, NUMBERS_SIZE));
    }

}
