package lotto.presentation.view;

import lotto.controller.dto.LottoPurchaseResult;
import lotto.controller.dto.LottoResult;

public class OutputConsoleView implements OutputView{
    @Override
    public void printLottos(LottoPurchaseResult lottos) {
        System.out.println(lottos.lottoNumbers().size() + "개를 구매했습니다.");
        lottos.lottoNumbers()
                .forEach(System.out::println);
    }

    @Override
    public void printStatistics(LottoResult result) {
        System.out.println("당첨 통계");
        System.out.println("---");

        result.results().forEach(rankResult ->
                System.out.printf("%s (%s원) - %d개%n",
                        rankResult.description(),
                        String.format("%,d", rankResult.reward()),
                        rankResult.count())
        );
    }

    @Override
    public void printResult(double calculateEarningRate) {
        System.out.printf("총 수익률은 %,.1f%%입니다.%n", calculateEarningRate);
    }

    @Override
    public void printError(String message) {
        System.out.println(message);
    }
}
