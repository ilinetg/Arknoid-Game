import java.util.List;

/**
 * LevelInformation interface.
 * @author gal
 */
public interface LevelInformation {
    /**
     * @return The number of balls in the level
     */
    int numberOfBalls();

    /**
     * gets the initial velocity of each ball. The size of the list will be
     * equal to the number of balls
     * @return A list of velocities for the balls
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return The paddles speed
     */
    int paddleSpeed();

    /**
     * @return The paddle width
     */
    int paddleWidth();

    /**
     * @return the levels name
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * A method to get the blocks that should appear at the level, each block
     * contains its size, color and location.
     * @return a list of all the blocks in the level
     */
    List<Block> blocks();

    /**
     * @return The number of blocks to be removed before the level is cleared
     */
    int numberOfBlocksToRemove();

}
