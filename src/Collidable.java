/**
 * Collidable class.
 * @author gal
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. and return new velocity.
     * @param collisionPoint
     *            the point where the collision occures
     * @param currentVelocity
     *            given velocity.
     * @param hitter
     *            the hiting object.
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}