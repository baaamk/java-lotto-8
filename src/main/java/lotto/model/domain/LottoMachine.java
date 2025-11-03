package lotto.model.domain;

import lotto.exception.ErrorMessage;
import lotto.model.strategy.Generator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class LottoMachine {

    private final Generator generator;

    public LottoMachine(Generator generator) {
        Objects.requireNonNull(generator, ErrorMessage.NULL_EXCEPTION.getMessage());
        this.generator = generator;
    }

    public Lottos publishLottos(int count) {
        List<Lotto> lottos = Stream.generate(generator::generate)
                .limit(count)
                .toList();
        return Lottos.of(lottos);
    }
}
