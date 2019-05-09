import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Ball class.
 * @author gal
 */
public class Ball implements Sprite {
    // memebers
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Bounds boundery;
    private GameEnvironment environment;

    /**
     * constructor.
     * @param x
     *            cordinate of the center Point of the ball.
     * @param y
     *            cordinate of the center Point of the ball.
     * @param r
     *            radius
     * @param color
     *            is the ball's color.
     * @param g
     *            the environment in which the ball moves at
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment g) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(2, 4);
        this.environment = g;
    }

    /**
     * constructor.
     * @param center
     *            the center Point of the ball.
     * @param r
     *            is the ball's radius.
     * @param color
     *            is the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(4, 4);
    }

    /**
     * constructor.
     * @param x
     *            cordinate of the center Point of the ball.
     * @param y
     *            cordinate of the center Point of the ball.
     * @param r
     *            is the ball's radius.
     * @param color
     *            is the ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(4, 4);
    }

    /**
     * constructor.
     * @param x
     *            cordinate of the center Point of the ball.
     * @param y
     *            cordinate of the center Point of the ball.
     * @param r
     *            is the ball's radius.
     * @param color
     *            is the ball's color.
     * @param vel
     *            is the ball's velocity.
     */
    public Ball(int x, int y, int r, Color color, Velocity vel) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = vel;
    }

    /**
     * constructor.
     * @param x
     *            cordinate of the center Point of the ball.
     * @param y
     *            cordinate of the center Point of the ball.
     * @param r
     *            is the ball's radius.
     * @param color
     *            is the ball's color.
     * @param bound
     *            is the ball's borders.
     */
    public Ball(int x, int y, int r, Color color, Bounds bound) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.boundery = bound;
        this.velocity = new Velocity(4, 4);
    }

    /**
     * constructor.
     * @param x
     *            cordinate of the center Point of the ball.
     * @param y
     *            cordinate of the center Point of the ball.
     * @param r
     *            is the ball's radius.
     * @param color
     *            is the ball's color.
     * @param vel
     *            is the ball's velocity.
     * @param bound
     *            is the ball's borders.
     */
    public Ball(int x, int y, int r, Color color, Velocity vel, Bounds bound) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = vel;
        this.boundery = bound;
    }

    /**
     * @return the ball's center point X cordinate valur
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the ball's center point Y cordinate value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return ball's Bounds.
     */
    public Bounds getBounds() {
        return this.boundery;
    }

    /**
     * @return the GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * sets the GameEnvironment.
     * @param g
     *            the GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.environment = g;
    }

    /**
     * this method draw the ball on the given DrawSurface.
     * @param surface
     *            the gui.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * @param v
     *            the velocity we give to the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx
     *            the change in x cordinate by velocity.
     * @param dy
     *            the change in x cordinate by velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moves the ball to the next point , if there is a collision we change the
     * velocity and then move the ball acording to the new velocity.
     * @param dt
     *            the frame time parameter.
     */
    public void moveOneStep(double dt) {
        Point end = this.getVelocity().applyToPoint(this.center, dt);
        Line trajectory = new Line(this.center, end);
        // if there is no collision , move the ball and return
        if (this.environment.getClosestCollision(trajectory) == null) {
            this.center = end;
            return;
        } else {
            CollisionInfo info = this.environment
                    .getClosestCollision(trajectory);
            Rectangle rectangle = info.collisionObject()
                    .getCollisionRectangle();
            Point close = trajectory
                    .closestIntersectionToStartOfLine(rectangle);
            double x;
            double y;
            if (this.velocity.getDx() <= 0) {
                x = Math.ceil(close.getX());
            } else {
                x = Math.floor(close.getX());
            }
            if (this.velocity.getDy() <= 0) {
                y = Math.ceil(close.getY());
            } else {
                y = Math.floor(close.getY());
            }
            Point closest = new Point(x, y);
            // change the ball velocity
            Collidable colidObj = this.environment
                    .getClosestCollision(trajectory).collisionObject();

            this.velocity = colidObj.hit(this, close, this.velocity);
            this.center = this.velocity.applyToPoint(this.center, dt);
        }
    }

    @Override
    /**
     * notify the sprite that time has passed
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * @param gameLevel
     *            -the game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * @param g
     *            the level we remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}