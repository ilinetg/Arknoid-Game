/**
 * Line class. Created by gal on 02/04/17.
 */
public class Line {
    // members
    private Point start;
    private Point end;

    /**
     * constructor.
     * @param start
     *            point of the line.
     * @param end
     *            point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * @param x1
     *            X cordinate of start point.
     * @param y1
     *            Y cordinate of start point.
     * @param x2
     *            X cordinate of end point.
     * @param y2
     *            Y cordinate of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param other
     *            the line we looking if our line intersect with him.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        double otherStartDx = other.start().getX();
        double otherStartDy = other.start().getY();
        double otherEndDx = other.end().getX();
        double otherEndDy = other.end().getY();
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();
        double m;
        double otherM;
        double b1;
        double b2;
        double optionX;
        double optionY;
        // if its the same line
        if (this.equals(other)) {
            return false;
        }
        // if our line parallel to the y axis and the other line parallel to the
        // x axis
        if ((startX == endX) && (otherStartDy == otherEndDy)) {
            if (Math.max(otherStartDx, otherEndDx) >= Math.max(startX, endX)) {
                return true;
            }
        }
        // if our line parallel to the x axis and the other line parallel to the
        // y axis
        if ((startY == endY) && (otherStartDx == otherEndDx)) {
            if (Math.max(otherStartDx, otherEndDx) <= Math.max(startX, endX)) {
                return true;
            }
        }
        // if our line parallel to y axis
        if (startX == endX) {
            // calculate the other lines incline
            otherM = (otherStartDy - otherEndDy) / (otherStartDx - otherEndDx);
            // calculate the other lines y value when intersect the x axis
            b2 = otherStartDy + (-1) * otherM * otherStartDx;
            // optional y value of intersect point
            optionY = otherM * startX + b2;
            if (((optionY <= Math.max(startY, endY))
                    && (optionY >= Math.min(startY, endY)))
                    && (optionY <= Math.max(otherStartDy, otherEndDy))
                    && (optionY >= Math.min(otherStartDy, otherEndDy))) {
                return true;
            }
        }
        // if other line parallel to y axis
        if (otherStartDx == otherEndDx) {
            m = (startY - endY) / (startX - endX);
            b1 = startY + (-1) * m * startX;
            optionY = m * otherStartDx + b1;
            if ((optionY <= Math.max(otherStartDy, otherEndDy))
                    && (optionY >= Math.min(otherStartDy, otherEndDy))
                    && ((optionY <= Math.max(startY, endY))
                            && (optionY >= Math.min(startY, endY)))) {
                return true;
            }
        }
        // if other line parallel to y axis
        if (otherStartDy == otherEndDy) {
            m = (startY - endY) / (startX - endX);
            b1 = startY + (-1) * m * startX;
            optionX = (other.start().getY() - b1) / m;
            if ((optionX <= Math.max(otherStartDx, otherEndDx))
                    && (optionX >= Math.min(otherStartDx, otherEndDx))
                    && (optionX <= Math.max(startX, endX))
                    && (optionX >= Math.min(startX, endX))) {
                return true;
            }
        }

        // if both lines doesnt parallel to any of the axis:
        // our line incline
        m = (startY - endY) / (startX - endX);
        // other line incline
        otherM = (otherStartDy - otherEndDy) / (otherStartDx - otherEndDx);
        b1 = startY + (-1) * m * startX;
        b2 = otherStartDy + (-1) * otherM * otherStartDx;
        optionX = (b2 - b1) / (m - otherM);
        optionY = m * optionX + b1;
        // if lines are parallel
        if (m == otherM) {
            return false;
        }
        // else
        if ((optionX <= Math.max(startX, endX)
                && optionX >= Math.min(startX, endX))
                && (optionX <= Math.max(otherStartDx, otherEndDx)
                        && optionX >= Math.min(otherStartDx, otherEndDx))) {
            if ((optionY <= Math.max(startY, endY)
                    && optionY >= Math.min(startY, endY))
                    && (optionY <= Math.max(otherStartDy, otherEndDy)
                            && optionY >= Math.min(otherStartDy, otherEndDy))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param other
     *            the line we looking for his intersect point with our line.
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the lines doesnt intersect
        if (!this.isIntersecting(other)) {
            return null;
        } else {
            double startX = this.start.getX();
            double startY = this.start.getY();
            double endX = this.end.getX();
            double endY = this.end.getY();
            double optionX;
            double optionY;
            double m = (startY - endY) / (startX - endX);
            double otherM = (other.start().getY() - other.end().getY())
                    / (other.start().getX() - other.end().getX());
            // for our line
            double b1;
            // for other line
            double b2;
            // if one of the lines is parallel to the axis
            if (startX == endX) {
                otherM = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                b2 = other.start().getY()
                        + (-1) * otherM * other.start().getX();
                optionY = otherM * startX + b2;
                return new Point(startX, optionY);
            }
            if (startY == endY) {
                otherM = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                b2 = other.start().getY()
                        + (-1) * otherM * other.start().getX();
                optionX = (startY - b2) / otherM;
                return new Point(optionX, startY);
            }
            if (other.start().getX() == other.end().getX()) {
                m = (startY - endY) / (startX - endX);
                b1 = startY + (-1) * m * startX;
                optionY = m * other.start().getX() + b1;
                return new Point(other.start().getX(), optionY);
            }
            if (other.start().getY() == other.end().getY()) {
                m = (startY - endY) / (startX - endX);
                b1 = startY + (-1) * m * startX;
                optionX = (other.start().getY() - b1) / m;
                return new Point(optionX, other.start().getY());
                // if no one of the lines parallel to the axis
            } else {
                m = (startY - endY) / (startX - endX);
                otherM = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                b1 = startY + (-1) * m * startX;
                // for other line
                b2 = other.start().getY()
                        + (-1) * otherM * other.start().getX();
                optionX = (b2 - b1) / (m - otherM);
                optionY = m * optionX + b1;
                return new Point(optionX, optionY);
            }
        }
    }

    /**
     * @param other
     *            the line we compare to our line
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        // check if the lines has the same start and end points- so they are
        // equal
        if ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end())
                        && this.end.equals(other.start()))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     * @param rect
     *            the rectangle we look for the intersection point with him
     * @return the closest intersection point with the rect
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // we get a list of intersection points wuth the rectangle
        java.util.List<Point> points = rect.intersectionPoints(this);
        Point closest = null;
        // if there are intersection points
        if (!points.isEmpty()) {
            double distance = this.start.distance(points.get(0));
            double temp = 0;
            for (int i = 0; i < points.size(); i++) {
                temp = this.start.distance(points.get(i));
                if (temp <= distance) {
                    distance = temp;
                    closest = points.get(i);
                }
            }
        }
        return closest;
    }
}