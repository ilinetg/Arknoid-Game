import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit class that implements LevelInformation - is one of the game levels.
 * @author gal
 */
public class DirectHit implements LevelInformation {
    // members
    private Background background;
    private Point center;

    /**
     * constructor.
     */
    public DirectHit() {
        this.background = new Background(Color.BLACK);
        this.center = new Point(395, 135);
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
        velocityOfBall.add(new Velocity(0.1, 240));
        return velocityOfBall;
    }

    /**
     * @return The paddles speed
     */
    @Override
    public int paddleSpeed() {
        return 400;
    }

    /**
     * @return The paddle width
     */
    @Override
    public int paddleWidth() {
        return 200;
    }

    /**
     * @return the levels name
     */
    @Override
    public String levelName() {
        return ("Direct Hit");
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        // creating three circles with 4 lines and adding them to the
        // background
        Circle c1 = new Circle(this.center, 80, Color.BLUE, false);
        Circle c2 = new Circle(this.center, 60, Color.BLUE, false);
        Circle c3 = new Circle(this.center, 40, Color.BLUE, false);
        this.background.getSprites().addSprite(c1);
        this.background.getSprites().addSprite(c2);
        this.background.getSprites().addSprite(c3);
        Point pS = new Point(this.center.getX() - 10, this.center.getY());
        Point pE = new Point(this.center.getX() - 90, this.center.getY());
        Bow b1 = new Bow(new Line(pS, pE), Color.BLUE);
        this.background.getSprites().addSprite(b1);
        pS = new Point(this.center.getX() + 5, this.center.getY());
        pE = new Point(this.center.getX() + 90, this.center.getY());
        Bow b2 = new Bow(new Line(pS, pE), Color.BLUE);
        this.background.getSprites().addSprite(b2);
        pS = new Point(this.center.getX(), this.center.getY() - 10);
        pE = new Point(this.center.getX(), this.center.getY() - 90);
        Bow b3 = new Bow(new Line(pS, pE), Color.BLUE);
        this.background.getSprites().addSprite(b3);
        pS = new Point(this.center.getX(), this.center.getY() + 10);
        pE = new Point(this.center.getX(), this.center.getY() + 90);
        Bow b4 = new Bow(new Line(pS, pE), Color.BLUE);
        this.background.getSprites().addSprite(b4);
        return this.background;
    }

    /**
     * A method to get the blocks that should appear at the level, each block
     * contains its size, color and location.
     * @return a list of all the blocks in the level
     */
    @Override
    public List<Block> blocks() {
        // creeating a blocks in the middle
        List<Block> blocks = new ArrayList<Block>();
        Point upperLeft = new Point(this.center.getX() - 10,
                this.center.getY() - 10);
        Block block = new Block(upperLeft, 20, 20, Color.red);
        blocks.add(block);
        return blocks;
    }

    /**
     * @return The number of blocks to be removed before the level is cleared
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
