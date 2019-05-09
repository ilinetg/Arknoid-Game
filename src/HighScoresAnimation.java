import java.awt.Color;
import java.io.File;

import biuoop.DrawSurface;

/**
 * HighScoresAnimation class that implements Animation.
 * @author gal
 */
public class HighScoresAnimation implements Animation {
    // members
    private HighScoresTable table;
    private String finish;
    private boolean stop;

    /**
     * constructor.
     * @param scores
     *            a HighScoresTable.
     * @param endKey
     *            the key to shut the animation.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.table = scores;
        this.finish = endKey;
        this.stop = false;
    }

    /**
     * incharge of game logic.
     * @param d
     *            the surface we draw on.
     * @param dt
     *            time concern.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        int i = 0;
        d.setColor(Color.green);
        d.drawText(100, d.getHeight() / 4, "Player", 30);
        d.drawText(d.getWidth() - 200, d.getHeight() / 4, "Scores", 30);
        d.setColor(Color.BLUE);
        this.table = HighScoresTable.loadFromFile(new File("highScores.txt"));
        for (; i < this.table.getHighScores().size(); i++) {
            ScoreInfo score = this.table.getHighScores().get(i);
            d.drawText(100, (d.getHeight() / 4) + (50 * (i + 1)),
                    score.getName(), 30);
            d.drawText(d.getWidth() - 200, (d.getHeight() / 4) + (50 * (i + 1)),
                    Integer.toString(score.getScore()), 30);

        }
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() - 100,
                "to continue press space", 30);

    }

    /**
     * @return if the game should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
