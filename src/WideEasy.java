import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class WideEasy that implements LevelInformation- one of the game levels.
 * @author gal
 */
public class WideEasy implements LevelInformation {
    // members
    private Background background;
    private Point center;

    /**
     * constructor.
     */
    public WideEasy() {
        this.background = new Background(Color.WHITE);
        this.center = new Point(200, 150);
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
        for (int i = 1; i < 6; i++) {
            velocityOfBall.add(Velocity.fromAngleAndSpeed(i * 10, 240));

        }
        for (int i = 1; i < 5; i++) {
            velocityOfBall.add(Velocity.fromAngleAndSpeed(i * (-10), 240));
        }
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
        return 600;
    }

    /**
     * @return the levels name
     */
    @Override
    public String levelName() {
        return ("Wize Easy");
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        // creatin a sun from three circles and sunbeam
        Circle c1 = new Circle(this.center, 70, new Color(240, 230, 175), true);
        Circle c2 = new Circle(this.center, 60, new Color(232, 220, 155), true);
        Circle c3 = new Circle(this.center, 50, new Color(222, 210, 135), true);
        this.background.getSprites().addSprite(c1);
        this.background.getSprites().addSprite(c2);
        this.background.getSprites().addSprite(c3);
        for (int i = 0; i < 130; i++) {
            Point end = new Point((6 * i), 250);
            Bow line = new Bow(new Line(this.center, end),
                    new Color(222, 210, 135));
            this.background.getSprites().addSprite(line);
        }

        return this.background;
    }

    /**
     * A method to get the blocks that should appear at the level, each block
     * contains its size, color and location.
     * @return a list of all the blocks in the level
     */
    @Override
    public List<Block> blocks() {
        // creating a row of blocks
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            if ((i - 2) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.RED);
                blocks.add(block);
                continue;
            } else if ((i - 4) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.ORANGE);
                blocks.add(block);
                continue;
            } else if ((i - 6) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.YELLOW);
                blocks.add(block);
                continue;
            } else if ((i - 9) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.green);
                blocks.add(block);
                continue;
            } else if ((i - 11) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.BLUE);
                blocks.add(block);
                continue;
            } else if ((i - 13) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.pink);
                blocks.add(block);
                continue;
            } else if ((i - 15) < 0) {
                Block block = new Block(
                        new Point(20.1 + (50.6 * blocks.size()), 250), 50.6, 30,
                        Color.CYAN);
                blocks.add(block);
                continue;
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
