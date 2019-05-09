import biuoop.DrawSurface;

/**
 * Animation interface.
 * @author gal
 */
public interface Animation {
    /**
     * incharge of game logic.
     * @param d
     *            the surface we draw on.
     * @param dt
     *            - timing parameter.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return if the game should stop
     */
    boolean shouldStop();
}
