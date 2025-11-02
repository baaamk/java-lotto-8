package lotto.presentation.view;

import lotto.controller.dto.LottoPurchaseResult;
import lotto.controller.dto.LottoResult;

public interface OutputView {
    void printLottos(LottoPurchaseResult lottos);

    void printStatistics(LottoResult result);

    void printResult(double calculateEarningRate);

    void printError(String message);
}
