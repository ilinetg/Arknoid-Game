import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class thats implement Animation.
 * @author gal
 */
public class KeyPressStoppableAnimation implements Animation {
    // members
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor
     *            - the keyBoardSensor.
     * @param key
     *            the key that exits the animation.
     * @param animation
     *            the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
            Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * incharge of game logic.
     * @param d
     *            the surface we draw on.
     * @param dt
     *            -timin frame parameter.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return if the game should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
