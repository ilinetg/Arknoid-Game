import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Paddle class- implement of Collidable and Sprite.
 * @author gal
 */
public class Paddle implements Collidable, Sprite {
    // members
    private biuoop.KeyboardSensor keyboard;
    private Block pad;
    private int speed;

    /**
     * constructor.
     * @param block
     *            the shape
     * @param gui
     *            the keybord sensor
     */
    public Paddle(Block block, GUI gui) {
        this.pad = block;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = 5;
    }

    /**
     * constructor.
     * @param block
     *            the shape
     * @param gui
     *            the keybord sensor
     * @param fast
     *            the paddle speed.
     */
    public Paddle(Block block, GUI gui, int fast) {
        this.pad = block;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = fast;
    }

    /**
     * this method moves the paddle to the left.
     * @param dt
     *            frames time parameter.
     */
    public void moveLeft(double dt) {
        Point upLeft = this.pad.getCollisionRectangle().getUpperLeft();
        // if we didnt cross the border of the screen
        if (upLeft.getX() - (this.speed * dt) >= 20) {
            Point p = new Point(upLeft.getX() - (this.speed * dt),
                    upLeft.getY());
            this.pad = new Block(p,
                    (int) this.pad.getCollisionRectangle().getWidth(),
                    (int) this.pad.getCollisionRectangle().getHeight(),
                    this.pad.getColor());
        } else {
            Point p = new Point(20, upLeft.getY());
            this.pad = new Block(p,
                    (int) this.pad.getCollisionRectangle().getWidth(),
                    (int) this.pad.getCollisionRectangle().getHeight(),
                    this.pad.getColor());
        }
    }

    /**
     * this method moves the paddle to the right.
     * @param dt
     *            frames time parameter.
     */
    public void moveRight(double dt) {
        Point upLeft = this.pad.getCollisionRectangle().getUpperLeft();
        Point upRight = this.pad.getCollisionRectangle().getUpperRight();
        // if we didnt cross the border of the screen
        if (upRight.getX() + (this.speed * dt) <= 780) {
            Point p = new Point(upLeft.getX() + (this.speed * dt),
                    upLeft.getY());
            this.pad = new Block(p,
                    (int) this.pad.getCollisionRectangle().getWidth(),
                    (int) this.pad.getCollisionRectangle().getHeight(),
                    this.pad.getColor());
        } else {
            Point p = new Point(
                    780 - this.pad.getCollisionRectangle().getWidth(),
                    upLeft.getY());
            this.pad = new Block(p,
                    (int) this.pad.getCollisionRectangle().getWidth(),
                    (int) this.pad.getCollisionRectangle().getHeight(),
                    this.pad.getColor());
        }
    }

    @Override
    /**
     * if the keys to move where pressed the method calls the right move method.
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed("d")
                || this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
        if (this.keyboard.isPressed("a")
                || this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
    }

    @Override
    /**
     * we draw the paddle on the screen.
     * @param d
     *            DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(
                (int) this.pad.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.pad.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.pad.getCollisionRectangle().getWidth(),
                (int) this.pad.getCollisionRectangle().getHeight());
        d.setColor(Color.YELLOW);
        d.fillRectangle(
                (int) this.pad.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.pad.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.pad.getCollisionRectangle().getWidth(),
                (int) this.pad.getCollisionRectangle().getHeight());
    }

    @Override
    /**
     * @return the shape of the paddle's block.
     */
    public Rectangle getCollisionRectangle() {
        return this.pad.getCollisionRectangle();
    }

    @Override
    /**
     * @param currentVelocity
     *            the velocity of the coliding object.
     * @param collisionPoint
     *            the point where the collision happands
     * @return the new velocity of the object after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        double length = this.pad.getCollisionRectangle().getWidth();
        double x = length / 5;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point p = this.pad.getCollisionRectangle().getUpperLeft();
        double newSpeed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double angle;
        // we divide the paddle to 5 even parts
        // if the object collided the paddle on the leftest part
        if (((p.getX() + x) >= collisionPoint.getX())
                && (p.getX() < collisionPoint.getX())) {
            angle = -60;
            return Velocity.fromAngleAndSpeed(angle, newSpeed);
        }
        // if the object collided the paddle on the second part
        if (((p.getX() + x) <= collisionPoint.getX())
                && ((p.getX() + 2 * x) > collisionPoint.getX())) {
            angle = -30;
            return Velocity.fromAngleAndSpeed(angle, newSpeed);
        }
        // if the object collided the paddle on the third(middle) part
        if (((p.getX() + 2 * x) <= collisionPoint.getX())
                && ((p.getX() + 3 * x) > collisionPoint.getX())) {
            return this.pad.hit(hitter, collisionPoint, currentVelocity);

        }
        // if the object collided the paddle on the fourth part
        if (((p.getX() + 3 * x) <= collisionPoint.getX())
                && ((p.getX() + 4 * x) > collisionPoint.getX())) {
            angle = 30;
            return Velocity.fromAngleAndSpeed(angle, newSpeed);
        }
        // if the object collided the paddle on the rightest part
        if (((p.getX() + 4 * x) <= collisionPoint.getX())
                && ((p.getX() + 5 * x) >= collisionPoint.getX())) {
            angle = 60;
            return Velocity.fromAngleAndSpeed(angle, newSpeed);
        }
        // if there was no collision on the upper side of the paddle
        return this.pad.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * Add this paddle to the game.
     * @param g
     *            Game object
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * changes the color of the paddle's block.
     * @param color
     *            Color
     */
    public void setColor(Color color) {
        this.pad.setColor(color);
    }

    /**
     * @return the block of the paddle.
     */
    public Block getBlock() {
        return this.pad;
    }

    /**
     * removes the paddle from the game level.
     * @param gameLevel
     *            the level we want to remove the paddle from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * @return paddel speed.
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * @param x
     *            the speed we want to set to the paddle.
     */
    public void setSpeed(int x) {
        this.speed = x;
    }
}
