package bowling.domain.state;

import bowling.domain.value.Pins;
import bowling.domain.value.Score;
import bowling.utils.Preconditions;

public class Spare extends FinishState {
    private static final String SPARE = "/";

    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        Preconditions.checkMaximumSize(firstPins.sum(secondPins), MAXIMUM_COUNT, "최대 투구수를 넘을 수 없습니다.");
        Preconditions.checkState(firstPins.isSpare(secondPins), "투구의 합이 10이 되어야 합니다.");

        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPin) {
        return new Spare(firstPins, secondPin);
    }

    @Override
    public State pitch(Pins pins) {
        throw new IllegalArgumentException(INVALID_PITCH);
    }

    @Override
    public Score calculateScore() {
        return Score.ofSpare();
    }

    @Override
    public String mark() {
        return checkGutter(firstPins) + DELIMITER + SPARE;
    }
}
