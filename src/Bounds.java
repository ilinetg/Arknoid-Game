/**
 * this class is set the border of each ball.
 * @author gal
 */
public class Bounds {
    // members
    private double uperBound;
    private double lowerBound;
    private double leftBound;
    private double rightBound;

    /**
     * @param rightwidth
     *            is the right border.
     * @param leftwidth
     *            is the left border.
     * @param uphight
     *            is the up border.
     * @param downhight
     *            is the low border.
     */
    public Bounds(int rightwidth, int leftwidth, int uphight, int downhight) {
        this.leftBound = leftwidth;
        this.rightBound = rightwidth;
        this.uperBound = uphight;
        this.lowerBound = downhight;
    }

    /**
     * @return the up border value.
     */
    public double getUperBound() {
        return this.uperBound;
    }

    /**
     * @return is the low border value.
     */
    public double getLowerBound() {
        return this.lowerBound;
    }

    /**
     * @return is the left border value.
     */
    public double getLeftBound() {
        return this.leftBound;
    }

    /**
     * @return is the right border value.
     */
    public double getRightBound() {
        return this.rightBound;
    }

    /**
     * @param n
     *            is the up border value we set to the object.
     */
    public void setUperBound(double n) {
        this.uperBound = n;
    }

    /**
     * @param n
     *            is the low border value we set to the object.
     */
    public void setLowerBound(double n) {
        this.lowerBound = n;
    }

    /**
     * @param n
     *            is the left border value we set to the object.
     */
    public void setLeftBound(double n) {
        this.leftBound = n;
    }

    /**
     * @param n
     *            is the right border value we set to the object.
     */
    public void setRightBound(double n) {
        this.rightBound = n;
    }
}
