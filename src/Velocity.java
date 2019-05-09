/**
 * class Velocity -specifies the change in position on the `x` and the `y` axes.
 * @author gal
 */
public class Velocity {
    // members.
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx
     *            change in position on the `x`axes.
     * @param dy
     *            change in position on the `y`axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     * @param p
     *            the point we change.
     * @param dt
     *            for timing parameter(with the frames change).
     * @return point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p, double dt) {
        double x = p.getX() + (this.dx * dt);
        double y = p.getY() + (this.dy * dt);
        return new Point(x, y);

    }

    /**
     * @return the change in x cordinate by velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the change in y cordinate by velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param n
     *            the change in x cordinate we set the velocity to do.
     */
    public void setDx(double n) {
        this.dx = n;
    }

    /**
     * @param n
     *            the change in y cordinate we set the velocity to do.
     */
    public void setDy(double n) {
        this.dy = n;
    }

    /**
     * @param angle
     *            the angle in whicj the object moves to.
     * @param speed
     *            the speed the object moves.
     * @return Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle + 270)) * speed;
        double dy = Math.sin(Math.toRadians(angle + 270)) * speed;
        // double dx = Math.sin(angle) * speed;
        // double dy = Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }
}