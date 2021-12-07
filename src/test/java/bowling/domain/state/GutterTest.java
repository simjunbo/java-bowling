package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GutterTest {
    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark() {
        State gutter = Gutter.of(Pins.from(5), Pins.from(0));

        assertThat(gutter.getMark()).isEqualTo("5|-");
    }

    @Test
    @DisplayName("거터 상태의 기록 검증")
    void gutter_mark2() {
        State gutter = Gutter.of(Pins.from(0), Pins.from(0));

        assertThat(gutter.getMark()).isEqualTo("-|-");
    }
}