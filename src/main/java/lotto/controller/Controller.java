package lotto.controller;

import lotto.controller.converter.LottoDtoConverter;
import lotto.controller.dto.LottoPurchaseResult;
import lotto.controller.dto.LottoResult;
import lotto.exception.ErrorMessage;
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
import java.util.Objects;
import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public Controller(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = Objects.requireNonNull(inputView, ErrorMessage.NULL_EXCEPTION.getMessage());
        this.outputView = Objects.requireNonNull(outputView, ErrorMessage.NULL_EXCEPTION.getMessage());
        this.lottoService = Objects.requireNonNull(lottoService, ErrorMessage.NULL_EXCEPTION.getMessage());
    }

    public void run() {
        Money money = getInputMoney();

        Lottos lottos = showLottos(money);

        showResult(lottos, money);
    }

    private Money getInputMoney() {
        return retryInput(() -> {
            int parsedMoney = MoneyParser.parse(inputView.inputPurchaseMoney());
            Objects.requireNonNull(parsedMoney, ErrorMessage.NULL_EXCEPTION.getMessage());
            return Money.from(parsedMoney);
        });
    }

    private Lottos showLottos(Money money) {
        if (money == null) {
            throw new IllegalStateException(ErrorMessage.STATE_EXCEPTION.getMessage());
        }
        Lottos lottos = lottoService.buy(money);

        LottoPurchaseResult lottoPurchaseResult = LottoDtoConverter.toDto(lottos);

        outputView.printLottos(lottoPurchaseResult);
        return lottos;
    }

    private void showResult(Lottos lottos, Money money) {
        if (money == null || lottos == null) {
            throw new IllegalStateException(ErrorMessage.STATE_EXCEPTION.getMessage());
        }
        LottoResult result = getLottoResult(lottos);
        outputView.printStatistics(result);
        outputView.printResult(lottoService.calculateEarningRate(result, money));
    }

    private LottoResult getLottoResult(Lottos lottos) {
        return retryInput(() -> {
            List<Integer> parsedWinningLotto = WinningLottoParser.parse(inputView.inputWinningNumbers());
            Objects.requireNonNull(parsedWinningLotto, ErrorMessage.NULL_EXCEPTION.getMessage());

            Lotto winningLotto = Lotto.from(parsedWinningLotto);

            return retryInput(() -> {
                int parsedBonusNumber = BonusNumberParser.parse(inputView.inputBonusNumber());
                Objects.requireNonNull(parsedBonusNumber, ErrorMessage.NULL_EXCEPTION.getMessage());

                BonusNumber bonusNumber = BonusNumber.from(parsedBonusNumber);
                return lottoService.matchWith(lottos, winningLotto, bonusNumber);
            });
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
