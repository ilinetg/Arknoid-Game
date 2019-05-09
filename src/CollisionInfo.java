/**
 * CollisionInfo class.
 * @author gal
 */
public class CollisionInfo {
    // members
    private Point collision;
    private Collidable object;

    /**
     * @param collision
     *            the collision point.
     * @param obj
     *            the collidable object
     */
    public CollisionInfo(Point collision, Collidable obj) {
        this.collision = collision;
        this.object = obj;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}