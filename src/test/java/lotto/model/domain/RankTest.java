package lotto.model.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Test
    @DisplayName("6개 일치 시 1등 반환")
    void of_6개일치_1등() {
        Rank rank = Rank.of(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 + 보너스 일치 시 2등 반환")
    void of_5개보너스_2등() {
        Rank rank = Rank.of(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개만 일치 시 3등 반환")
    void of_5개_3등() {
        Rank rank = Rank.of(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 일치 시 4등 반환")
    void of_4개_4등() {
        Rank rank = Rank.of(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 시 5등 반환")
    void of_3개_5등() {
        Rank rank = Rank.of(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 NONE 반환")
    void of_2개이하_NONE() {
        Rank rank = Rank.of(2, false);
        assertThat(rank).isEqualTo(Rank.NONE);
    }

    @Test
    @DisplayName("calculatePrize()는 count 개수만큼 상금을 곱한다")
    void calculatePrize_정상() {
        Rank rank = Rank.FIFTH;
        int count = 3;

        int prize = rank.calculatePrize(count);

        assertThat(prize).isEqualTo(15_000);
    }

    @Test
    @DisplayName("getReward()와 getDescription() 정상 반환")
    void getter_검증() {
        assertThat(Rank.SECOND.getReward()).isEqualTo(30_000_000);
        assertThat(Rank.SECOND.getDescription()).isEqualTo("5개 일치, 보너스 볼 일치");
    }
}
