import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalFour class that implements LevelInformation - is one of the games
 * levels.
 * @author gal
 */
public class FinalFour implements LevelInformation {
    private Background background;
    private Point center;
    private Point otherCenter;

    /**
     * constructor.
     */
    public FinalFour() {
        this.background = new Background(new Color(20, 120, 200));
        this.center = new Point(100, 400);
        this.otherCenter = new Point(700, 500);
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
        velocityOfBall.add(Velocity.fromAngleAndSpeed(30, 250));
        velocityOfBall.add(Velocity.fromAngleAndSpeed(-30, 250));
        velocityOfBall.add(Velocity.fromAngleAndSpeed(60, 210));
        return velocityOfBall;
    }

    /**
     * @return The paddles speed
     */
    @Override
    public int paddleSpeed() {
        return 600;
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
        return ("Final Four");
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        Bow bow;
        Circle c;
        // adding the left clouds with the bows
        Point cloudLeftPoint = new Point(this.center.getX(),
                this.center.getY());
        c = new Circle(cloudLeftPoint, 35, Color.GRAY, true);
        this.background.getSprites().addSprite(c);
        for (int i = 0; i < 3; i++) {
            if (i % 2 == 0) {
                cloudLeftPoint = new Point(this.center.getX() - (10 * i),
                        this.center.getY() + (10 * i));
                c = new Circle(cloudLeftPoint, 35, new Color(170, 170, 170),
                        true);
                this.background.getSprites().addSprite(c);
            }
            cloudLeftPoint = new Point(this.center.getX() + (20 * i),
                    this.center.getY() + (15 * i));
            c = new Circle(cloudLeftPoint, 35, new Color(187, 187, 187), true);
            this.background.getSprites().addSprite(c);
        }
        cloudLeftPoint = new Point(this.center.getX() - 30,
                this.center.getY() + 40);
        c = new Circle(cloudLeftPoint, 35, Color.WHITE, true);
        this.background.getSprites().addSprite(c);
        for (int j = 0; j < 15; j++) {
            bow = new Bow(new Line(
                    new Point(this.center.getX() - 20 + (6 * j),
                            this.center.getY() + 20 + (4 * j)),
                    new Point(this.center.getX() - 100 + (50 * j),
                            this.center.getY() + 400)),
                    Color.white);
            this.background.getSprites().addSprite(bow);
        }

        // Right clouds and bows
        Point cloudRightPoint = this.otherCenter;
        c = new Circle(cloudRightPoint, 35, Color.GRAY, true);
        this.background.getSprites().addSprite(c);
        for (int i = 0; i < 3; i++) {
            if (i % 2 == 0) {
                cloudRightPoint = new Point(this.otherCenter.getX() - (10 * i),
                        this.otherCenter.getY() + (10 * i));
                c = new Circle(cloudRightPoint, 35, new Color(170, 170, 170),
                        true);

                this.background.getSprites().addSprite(c);

            }
            cloudRightPoint = new Point(this.otherCenter.getX() + (20 * i),
                    this.otherCenter.getY() + (15 * i));
            c = new Circle(cloudRightPoint, 35, new Color(187, 187, 187), true);
            this.background.getSprites().addSprite(c);
        }
        cloudRightPoint = new Point(this.otherCenter.getX() - 30,
                this.otherCenter.getY() + 40);
        c = new Circle(cloudRightPoint, 35, Color.WHITE, true);
        this.background.getSprites().addSprite(c);
        for (int j = 0; j < 15; j++) {
            bow = new Bow(
                    new Line(
                            new Point(this.otherCenter.getX() - 15 + (5 * j),
                                    this.otherCenter.getY() + 10 + (5 * j)),
                            new Point(this.otherCenter.getX() + (6 * j),
                                    this.otherCenter.getY() + 100)),
                    Color.WHITE);
            this.background.getSprites().addSprite(bow);
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
        Block block;
        ArrayList<Color> light = new ArrayList<Color>();
        light.add(Color.GRAY);
        light.add(Color.RED);
        light.add(Color.YELLOW);
        light.add(Color.GREEN);
        light.add(Color.WHITE);
        light.add(Color.PINK);
        light.add(Color.CYAN);
        List<Block> blocks = new ArrayList<Block>();
        // creating 7 full rows of blocks in different colors
        for (int i = 15; i > 8; i--) {
            Point p = new Point(780, 150 + (20 * (12 - i)));
            Color color = light.get(15 - i);
            for (int j = 0; j < 15; j++) {
                p = new Point((p.getX() - 50.6), p.getY());
                block = new Block(p, 50.6, 20);
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
