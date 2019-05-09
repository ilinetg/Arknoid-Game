import java.awt.Color;

import biuoop.DrawSurface;

/**
 * ScoreIndicator class that implements Sprite.
 * @author gal
 */
public class ScoreIndicator implements Sprite {
    // members
    private Counter score;

    /**
     * constructor.
     * @param count
     *            the score.
     */
    public ScoreIndicator(Counter count) {
        this.score = count;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // draw a rect with the score
        d.setColor(Color.red);
        d.fillRectangle(200, 0, 300, 30);
        d.setColor(Color.black);
        d.drawText(340, 20, "Score:" + Integer.toString(this.score.getValue()),
                15);
    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add this object to the game.
     * @param gameLevel
     *            its the Game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
