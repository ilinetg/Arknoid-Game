/**
 * Point class.
 * @author gal
 */
public class Point {
    // members
    private double dx;
    private double dy;

    /**
     * CONSTRUCTOR.
     * @param x
     *            the x cordinate.
     * @param y
     *            the Y cordinate.
     */
    public Point(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * @param other
     *            the point we are checking the distanse to her from our point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt((Math.pow(this.dx - other.getX(), 2))
                + (Math.pow(this.dy - other.getY(), 2)));
    }

    /**
     * @param other
     *            second point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        // cheking that the x cordinate equals the other x cordinate , and the
        // same to y cordinate
        if (this.dx == other.getX()) {
            if (this.dy == other.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the x and
     */
    public double getX() {
        return this.dx;
    }

    /**
     * @return the y values of this point
     */
    public double getY() {
        return this.dy;
    }

    /**
     * set the dx value.
     * @param x
     *            the value for dx
     */
    public void setX(double x) {
        this.dx = x;
    }

    /**
     * set the dy value.
     * @param y
     *            the value for dy
     */
    public void setY(double y) {
        this.dy = y;
    }
}
