import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class- runs an animation.
 * @author gal
 */
public class AnimationRunner {
    // members
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private double dt;

    /**
     * constructor.
     * @param gameGui
     *            - the game gui.
     * @param frameSec
     *            -timing parameter.
     */
    public AnimationRunner(GUI gameGui, int frameSec) {
        this.framesPerSecond = frameSec;
        this.gui = gameGui;
        this.dt = 1.0 / this.framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * this methosd runs the unimation until she should stop.
     * @param animation
     *            the animation we run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}