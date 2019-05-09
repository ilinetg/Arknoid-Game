import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Game class implements Animation interface.
 * @author gal
 */
public class GameLevel implements Animation {
    // members
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter counter;
    private Counter ballCounter;
    private Counter score;
    private Counter numOfLives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation info;
    private ScoreTrackingListener scoring;
    private ScoreIndicator scores;
    private LivesIndicator life;

    /**
     * constructor.
     * @param level
     *            information about the level
     * @param scores
     *            a Counter of the score until now
     * @param scoringGame
     *            a listener for updating score of the player
     * @param scoresInd
     *            a sprite of the score
     * @param live
     *            a sprite for the player left lives
     * @param gui
     *            the gui of the game
     */
    public GameLevel(LevelInformation level, Counter scores,
            ScoreTrackingListener scoringGame, ScoreIndicator scoresInd,
            LivesIndicator live, GUI gui) {
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counter = new Counter();
        this.ballCounter = new Counter();
        this.score = scores;
        this.info = level;
        this.sprites.addSprite(this.info.getBackground());
        this.scoring = scoringGame;
        this.scores = scoresInd;
        this.life = live;
        this.numOfLives = this.life.getLives();
    }

    /**
     * @param c
     *            a Colliadable we want to remove
     */
    public void removeCollidable(Collidable c) {
        try {
            for (Collidable i : this.environment.getEnvironment()) {
                // check if the colliadable exists in our gameEnviroment
                if (i == c) {
                    this.environment.getEnvironment().remove(c);
                    this.removeSprite((Sprite) c);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("list of collidables is empty");
            return;
        }
    }

    /**
     * @param s
     *            the sprite we want to remove from the game.
     */
    public void removeSprite(Sprite s) {
        try {
            for (Sprite i : this.sprites.getSprites()) {
                // check if we have this sprite
                if (i == s) {
                    this.sprites.getSprites().remove(s);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("list of collidables is empty");
            return;
        }
    }

    /**
     * @param c
     *            the collidable that we add to the list of collidables.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s
     *            the sprite that we add to the list of sprites.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * @return ArrayList of sprites.
     */
    public SpriteCollection getSpriteCollect() {
        return this.sprites;
    }

    /**
     * Initialize a new game: create the Blocks and Ball(tow balls), (and
     * Paddle) and add them to the game.
     */
    public void initialize() {
        // remove block listener
        BlockRemover remover = new BlockRemover(this, this.counter);
        // remove ball listener
        BallRemover ballRemove = new BallRemover(this, this.ballCounter);
        // the level name sprite
        LevelName name = new LevelName(this.info.levelName());
        name.addToGame(this);
        // creating the bound blocks
        boundery();
        // creating the death-region block
        Point p3 = new Point(20, 798);
        Block block3 = new Block(p3, 760, 20);
        block3.setHitNum(0);
        block3.addToGame(this);
        block3.addHitListener(ballRemove);
        this.keyboard = this.gui.getKeyboardSensor();
        // adding the score and the life sprites to the game
        this.scores.addToGame(this);
        this.life.addToGame(this);
        List<Block> blocks = this.info.blocks();
        int numBlocks = this.info.numberOfBlocksToRemove();
        // adding the blocks to the game
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).getCollisionRectangle().getHeight() != 0) {
                blocks.get(i).addToGame(this);
                blocks.get(i).addHitListener(scoring);
                blocks.get(i).addHitListener(remover);
                this.counter.increase(1);
            }
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        int pX = 800 - this.info.paddleWidth();
        // creating the paddle and adding him to the game
        Paddle pad = new Paddle(
                new Block(new Point(pX / 2, 578), this.info.paddleWidth(), 20),
                this.gui);
        // set paddle speed
        pad.setSpeed(this.info.paddleSpeed());
        pad.setColor(Color.YELLOW);
        pad.addToGame(this);
        // creating balls and adding them to the game
        this.createBallsOnTopOfPaddle();
        this.runner = new AnimationRunner(this.gui, 60);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        this.running = true;
        runner.run(this);
        pad.removeFromGame(this);
    }

    /**
     * runs the game until we lose all our lives.
     */
    public void run() {
        while (this.numOfLives.getValue() != 0) {
            this.playOneTurn();
        }
        gui.close();
    }

    /**
     * create 4 blocks in the edge of the screen.
     */
    public void boundery() {
        Point p1 = new Point(0, 30);
        Block block1 = new Block(p1, 800, 20);
        block1.setHitNum(0);
        block1.addToGame(this);

        Point p2 = new Point(0, 20);
        Block block2 = new Block(p2, 20, 780);
        block2.setHitNum(0);
        block2.addToGame(this);

        Point p4 = new Point(780, 30);
        Block block4 = new Block(p4, 20, 780);
        block4.setHitNum(0);
        block4.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.runner = new AnimationRunner(this.gui, 60);
        if (this.keyboard.isPressed("p")) {
            // PauseScreen pause = new PauseScreen();
            KeyPressStoppableAnimation pausePress = new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen());
            this.runner.run(pausePress);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        // if we finished the level
        if (this.counter.getValue() == 0) {
            this.score.increase(100);
        }
        // if we lost all balls in this turn
        if (this.ballCounter.getValue() == 0) {
            this.life.getLives().decrease(1);
        }
        this.running = (this.counter.getValue() != 0)
                && (this.ballCounter.getValue() != 0);
    }

    /**
     * creats the balls for this level.
     */
    public void createBallsOnTopOfPaddle() {
        Ball ball;
        int n = this.info.numberOfBalls();
        Point start = new Point(400, 580);
        // creating balls and adding them to the game
        for (int i = 0; i < n; i++) {
            if (n == 1) {
                ball = new Ball((int) start.getX() - 10,
                        (int) start.getY() - 10, 5, java.awt.Color.white,
                        environment);
                ball.setVelocity(this.info.initialBallVelocities().get(i));
                ball.addToGame(this);
                this.ballCounter.increase(1);
                continue;

            }
            if (i % 2 == 0) {
                ball = new Ball((int) start.getX() - (20 * (i + 1)),
                        410 + (5 * i), 5, java.awt.Color.white, environment);
                ball.setVelocity(this.info.initialBallVelocities().get(i));
                ball.addToGame(this);
                this.ballCounter.increase(1);
                continue;
            }
            ball = new Ball((int) start.getX() + (20 * (i + 1)), 410 + (5 * i),
                    5, java.awt.Color.white, environment);
            ball.setVelocity(this.info.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }

    /**
     * @return number of blocks we need to remove fri=om the game.
     */
    public int numOfBlockRemain() {
        return this.counter.getValue();
    }

    /**
     * @return number of lives left.
     */
    public int numOfLives() {
        return this.life.getLives().getValue();
    }
}
