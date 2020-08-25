package bowling.domian.frame;

import java.util.Optional;

public class FrameResult {
    private static final Integer INITIAL_SCORE = -1;

    private int score;
    private String desc;
    private int totalScore;

    public FrameResult(int score, String desc) {
        this.score = score;
        this.desc = desc;
        this.totalScore = INITIAL_SCORE;
    }

    public static FrameResult of(String desc) {
        return new FrameResult(INITIAL_SCORE, desc);
    }

    public static FrameResult of(int score, String desc) {
        return new FrameResult(score, desc);
    }

    public static FrameResult of(Score score, String desc) {
        if (score.isCalculateDone()) {
            return new FrameResult(score.getScore(), desc);
        }

        return new FrameResult(INITIAL_SCORE, desc);
    }

    public static FrameResult ofFinal(Score normal, Optional<Score> bonus, String desc) {
        return bonus
                .map(value -> new FrameResult(normal.getScore() + value.getScore(), desc))
                .orElseGet(() -> of(normal, desc));

    }

    public int getScore() {
        return this.score;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isCalculateDone() {
        return this.score != INITIAL_SCORE;
    }

    public boolean canAddTotal() {
        return score != INITIAL_SCORE;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int addTotalScore(int lastTotalScore) {
        if (!canAddTotal()) {
            return lastTotalScore;
        }
        this.totalScore = lastTotalScore + this.score;

        return this.totalScore;
    }

    @Override
    public String toString() {
        return "FrameResult{" +
                "score=" + score +
                ", desc='" + desc + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}