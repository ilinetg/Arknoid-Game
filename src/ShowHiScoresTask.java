import biuoop.KeyboardSensor;

/**
 * ShowHiScoresTask class that implements Task<Void>.
 * @author gal
 */
public class ShowHiScoresTask implements Task<Void> {
    // members
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboard;

    /**
     * @param runner
     *            the animation runnner.
     * @param highScoresAnimation
     *            the animation we run.
     * @param sensor
     *            the keyboardSensor.
     */
    public ShowHiScoresTask(AnimationRunner runner,
            Animation highScoresAnimation, KeyboardSensor sensor) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboard = sensor;
    }

    @Override
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                this.keyboard.SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}