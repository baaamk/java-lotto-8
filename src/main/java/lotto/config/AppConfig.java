package lotto.config;

import lotto.controller.Controller;
import lotto.model.domain.LottoMachine;
import lotto.model.service.LottoService;
import lotto.model.service.LottoServiceImpl;
import lotto.model.strategy.Generator;
import lotto.model.strategy.LottoGenerator;
import lotto.presentation.view.InputConsoleView;
import lotto.presentation.view.InputView;
import lotto.presentation.view.OutputConsoleView;
import lotto.presentation.view.OutputView;

public class AppConfig {
    public Controller controller() {
        return new Controller(inputView(), outputView(), lottoService());
    }

    private InputView inputView() {
        return new InputConsoleView();
    }

    private OutputView outputView() {
        return new OutputConsoleView();
    }

    private LottoService lottoService() {
        return new LottoServiceImpl(lottoMachine());
    }

    private LottoMachine lottoMachine() {
        return new LottoMachine(generator());
    }

    private Generator generator() {
        return new LottoGenerator();
    }
}
