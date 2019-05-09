import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Circle class that implements Sprite.
 * @author gal
 */
public class Circle implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private boolean filled;

    /**
     * constructor.
     * @param p
     *            the center of the circle.
     * @param r
     *            the circle radius.
     * @param col
     *            the circle color.
     * @param isFilled
     *            indicates if we want to fill the circle with color.
     */
    public Circle(Point p, int r, Color col, boolean isFilled) {
        this.center = p;
        this.radius = r;
        this.color = col;
        this.filled = isFilled;
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                this.radius);
        if (filled) {
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                    this.radius);
        }
    }

    /**
     * @param dt
     *            is for the frames time. notify the sprite that time has
     *            passed.
     */
    @Override
    public void timePassed(double dt) {
        // do nothing
    }
}
