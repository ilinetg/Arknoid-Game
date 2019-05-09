import java.util.ArrayList;

/**
 * Rectangle Class.
 * @author gal
 */

class Rectangle {
    // members
    private Point upLeft;
    private double width;
    private double height;

    /**
     * Constructor - create a new rectangle with location and width/height.
     * @param upperLeft
     *            upper left point location.
     * @param width
     *            rectangle's width
     * @param height
     *            rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @return a Line's array of the rectangle edges.
     */
    public ArrayList<Line> rectEdges() {
        // we create the lines of the rectangle
        Line upLine = new Line(this.upLeft, this.getUpperRight());
        Line downLine = new Line(this.getLowerLeft(), this.getLowerRight());
        Line leftLine = new Line(this.getLowerLeft(), this.upLeft);
        Line rightLine = new Line(this.getUpperRight(), this.getLowerRight());
        ArrayList<Line> rectLines = new ArrayList<Line>();
        // add the lines to the array
        rectLines.add(upLine);
        rectLines.add(downLine);
        rectLines.add(leftLine);
        rectLines.add(rightLine);
        return rectLines;
    }

    /**
     * @param line
     *            the line we want to check his intersections with the
     *            rectangle.
     * @return a (possibly empty) List of intersection points with the specified
     *         line.
     */
    public java.util.List intersectionPoints(Line line) {
        java.util.List<Line> rectLines = this.rectEdges();
        java.util.List<Point> pointIntersect = new ArrayList<Point>();
        // find all the intersection points and put them in the list
        for (int i = 0; i < rectLines.size(); i++) {
            if (line.intersectionWith(rectLines.get(i)) != null) {
                pointIntersect.add(line.intersectionWith(rectLines.get(i)));
            }
        }
        return pointIntersect;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the uuper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upLeft;
    }

    /**
     * @return the uuper right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upLeft.getX() + this.width, this.upLeft.getY());
    }

    /**
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upLeft.getX(), this.upLeft.getY() + this.height);
    }

    /**
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.getUpperLeft().getX() + this.width,
                this.getUpperRight().getY() + this.height);

    }

    /**
     * @param point
     *            the new point we want to set the upper left point to be.
     */
    public void setUpleft(Point point) {
        this.upLeft = point;
    }
}