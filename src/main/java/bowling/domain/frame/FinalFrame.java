package bowling.domain.frame;

import bowling.domain.state.*;
import bowling.domain.value.FrameNumber;
import bowling.domain.value.Pins;
import bowling.domain.value.Score;

import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {
    private static final String DELIMITER = "|";
    private static final int MAXIMUM_PITCH_COUNT = 3;
    private static final int MAXIMUM_SPARE_STATE_COUNT = 2;
    private static final int FINAL_FRAME_NUMBER = 10;

    private final LinkedList<State> states;

    private FinalFrame() {
        this.score = Score.init();
        this.states = new LinkedList<>(Collections.singletonList(Ready.of()));
        this.frameNumber = FrameNumber.from(FINAL_FRAME_NUMBER);
    }

    public static Frame create() {
        return new FinalFrame();
    }

    @Override
    public void pitch(Pins pins) {
        changeState(pins);
    }

    private void changeState(Pins pins) {
        State lastState = states.getLast();

        if (lastState.isFinish()) {
            changeBonusState(pins);
            return;
        }

        states.removeLast();
        states.add(lastState.pitch(pins));
    }

    private void changeBonusState(Pins pins) {
        State ready = Ready.of();
        states.add(ready.pitch(pins));
    }

    @Override
    public boolean isFrameOver() {
        return false;
    }

    @Override
    public boolean isGameOver() {
        State lastState = states.getLast();
        if (lastState instanceof Miss || lastState instanceof SecondGutter) {
            return true;
        }

        if (MAXIMUM_SPARE_STATE_COUNT == states.size() && hasSpare()) {
            return true;
        }

        return MAXIMUM_PITCH_COUNT == states.size();
    }

    private boolean hasSpare() {
        return states.stream()
                .anyMatch(Spare.class::isInstance);
    }

    @Override
    public void accumulateScore() {
        int accumulatePins = states.stream()
                .map(State::countPins)
                .reduce(0, Integer::sum);

        score = Score.of(accumulatePins);
    }

    @Override
    public String getMark() {
        return states.stream()
                .map(State::getMark)
                .collect(Collectors.joining(DELIMITER));
    }
}
