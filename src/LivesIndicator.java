import java.awt.Color;

import biuoop.DrawSurface;

/**
 * LivesIndicator class that implements Sprite.
 * @author gal
 */
public class LivesIndicator implements Sprite {
    // members
    private Counter life;

    /**
     * constructor.
     * @param count
     *            counter of the player lives in the game.
     */
    public LivesIndicator(Counter count) {
        this.life = count;
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.red);
        d.fillRectangle(0, 0, 200, 30);
        d.setColor(Color.black);
        d.drawText(30, 20, "Lives:" + Integer.toString(this.life.getValue()),
                15);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt
     *            frame's timing parameter.
     */
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

    /**
     * @return the counter of the players life.
     */
    public Counter getLives() {
        return this.life;
    }
}
