import biuoop.DrawSurface;

/**
 * Sprite Interface.
 * @author gal
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt
     *            frame's timing parameter.
     */
    void timePassed(double dt);
}