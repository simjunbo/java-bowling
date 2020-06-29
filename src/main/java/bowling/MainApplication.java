package bowling;

import bowling.domain.GamePlay;
import bowling.domain.Player;
import bowling.strategy.BowlingStrategy;
import bowling.strategy.NormalInputBowlingStrategy;
import bowling.view.InputView;

public class MainApplication {

    public void run(BowlingStrategy bowlingStrategy) {
        Player player = new Player(InputView.InputPlayerName());
        GamePlay gamePlay = GamePlay.play(player, bowlingStrategy);
    }

    public static void main(String[] args) {
        new MainApplication().run(new NormalInputBowlingStrategy());
    }

}