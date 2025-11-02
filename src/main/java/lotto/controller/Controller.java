package lotto.controller;

import lotto.controller.converter.LottoDtoConverter;
import lotto.controller.dto.LottoPurchaseResult;
import lotto.controller.dto.LottoResult;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.vo.BonusNumber;
import lotto.model.domain.vo.Money;
import lotto.model.service.LottoService;
import lotto.presentation.BonusNumberParser;
import lotto.presentation.MoneyParser;
import lotto.presentation.WinningLottoParser;
import lotto.presentation.view.InputView;
import lotto.presentation.view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public Controller(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        Money money = getInputMoney();

        Lottos lottos = showLottos(money);

        showResult(lottos, money);
    }

    private void showResult(Lottos lottos, Money money) {
        LottoResult result = getLottoResult(lottos);
        outputView.printStatistics(result);
        outputView.printResult(lottoService.calculateEarningRate(result, money));
    }

    private Lottos showLottos(Money money) {
        Lottos lottos = lottoService.buy(money);

        LottoPurchaseResult lottoPurchaseResult = LottoDtoConverter.toDto(lottos);

        outputView.printLottos(lottoPurchaseResult);
        return lottos;
    }

    private Money getInputMoney() {
        return retryInput(() -> {
            int parsedMoney = MoneyParser.parse(inputView.inputPurchaseMoney());
            return Money.from(parsedMoney);
        });
    }

    private LottoResult getLottoResult(Lottos lottos) {
        return retryInput(()->{
            List<Integer> parsedWinningLotto = WinningLottoParser.parse(inputView.inputWinningNumbers());
            Lotto winningLotto = Lotto.from(parsedWinningLotto);
            int parsedBonusNumber = BonusNumberParser.parse(inputView.inputBonusNumber());
            BonusNumber bonusNumber = BonusNumber.from(parsedBonusNumber);
            return lottoService.matchWith(lottos, winningLotto, bonusNumber);
        });
    }

    private <T> T retryInput(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }

    }
}
