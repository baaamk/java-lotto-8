package lotto.model.domain;

public class WinningLotto {
    private final Lotto winningLotto;

    private WinningLotto(Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLotto from(Lotto winningLotto) {
        return new WinningLotto(winningLotto);
    }

}
