import java.awt.Color;

import biuoop.DrawSurface;

/**
 * LevelName class that implements Sprite.
 * @author gal
 */
public class LevelName implements Sprite {
    // members
    private String name;

    /**
     * constructor.
     * @param levelName
     *            the game's name.
     */
    public LevelName(String levelName) {
        this.name = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(500, 0, 3000, 30);
        d.setColor(Color.red);
        d.fillRectangle(500, 0, 300, 30);
        d.setColor(Color.black);
        d.drawText(520, 20, "Level Name:" + this.name, 15);
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
