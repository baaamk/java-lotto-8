package lotto.model.domain;

import lotto.exception.ErrorMessage;

import java.util.List;
import java.util.Objects;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        Objects.requireNonNull(lottos, ErrorMessage.NULL_EXCEPTION.getMessage());
        return new Lottos(lottos);
    }


    public List<Lotto> values() {
        return List.copyOf(lottos);
    }

}
