import java.awt.Color;

import biuoop.DrawSurface;

/**
 * CountdownAnimation class that implements Animation. The CountdownAnimation
 * will display the given gameScreen, for numOfSeconds seconds, and on top of
 * them it will show a countdown from countFrom back to 1, where each number
 * will appear on the screen for (numOfSeconds / countFrom) secods, before it is
 * replaced with the next one.
 * @author gal
 */
public class CountdownAnimation implements Animation {
    // members
    private double seconds;
    private int counting;
    private SpriteCollection spritesInGame;
    private double startTime;
    private double endTime;

    /**
     * constructor.
     * @param numOfSeconds
     *            number of seconds a count-down will be display
     * @param countFrom
     *            from weach number the counting back starts
     * @param gameScreen
     *            the given sprites
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen) {
        this.counting = countFrom;
        this.seconds = numOfSeconds / countFrom;
        this.spritesInGame = gameScreen;
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.startTime = System.currentTimeMillis();
        this.spritesInGame.drawAllOn(d);
        d.setColor(Color.blue);
        d.drawText((d.getWidth() / 2), d.getHeight() / 2,
                Integer.toString(this.counting), 100);
        if ((this.startTime - this.endTime) >= (this.seconds * 1000)) {
            this.counting--;
            this.endTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.counting == 0) {
            return true;
        }
        return false;

    }
}