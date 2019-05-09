import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class Green3 that implements LevelInformation- is a level in the game.
 * @author gal
 */
public class Green3 implements LevelInformation {
    // members
    private Background background;

    /**
     * constructor.
     */
    public Green3() {
        this.background = new Background(Color.GREEN);
    }

    /**
     * @return The number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * @return A list of velocities for the balls
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityOfBall = new ArrayList<Velocity>();
        velocityOfBall.add(Velocity.fromAngleAndSpeed(25, 250));
        velocityOfBall.add(Velocity.fromAngleAndSpeed(-25, 250));
        return velocityOfBall;
    }

    /**
     * @return The paddles speed
     */
    @Override
    public int paddleSpeed() {
        return 300;
    }

    /**
     * @return The paddle width
     */
    @Override
    public int paddleWidth() {
        return 300;
    }

    /**
     * @return the levels name
     */
    @Override
    public String levelName() {
        return ("Green 3");
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        // adds a building with Antena to the background
        Building build = new Building();
        this.background.getSprites().addSprite(build);
        return this.background;
    }

    /**
     * A method to get the blocks that should appear at the level, each block
     * contains its size, color and location.
     * @return a list of all the blocks in the level
     */
    @Override
    public List<Block> blocks() {
        Block block;
        ArrayList<Color> light = new ArrayList<Color>();
        light.add(Color.GRAY);
        light.add(Color.RED);
        light.add(Color.YELLOW);
        light.add(Color.BLUE);
        light.add(Color.WHITE);
        List<Block> blocks = new ArrayList<Block>();
        // creating 5 rows of blocks in 5 colors
        for (int i = 12; i >= 8; i--) {
            Point p = new Point(780, 150 + (20 * (12 - i)));
            Color color = light.get(12 - i);
            for (int j = 0; j < i; j++) {
                p = new Point((p.getX() - 40), p.getY());
                block = new Block(p, 40, 20);
                block.setColor(color);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * @return The number of blocks to be removed before the level is cleared
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

}
