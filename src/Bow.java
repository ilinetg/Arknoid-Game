import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Bow class that implements Sprite.
 * @author gal
 */
public class Bow implements Sprite {
    // members
    private Line bow;
    private Color color;

    /**
     * constructor.
     * @param line
     *            - the line we draw.
     * @param col
     *            the color we draw with.
     */
    public Bow(Line line, Color col) {
        this.bow = line;
        this.color = col;
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.bow.start().getX(), (int) this.bow.start().getY(),
                (int) this.bow.end().getX(), (int) this.bow.end().getY());
    }

    /**
     * @param dt
     *            the time for frames changing. notify the sprite that time has
     *            passed.
     */
    @Override
    public void timePassed(double dt) {
        // do nothing
    }
}
