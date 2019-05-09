import java.util.ArrayList;

/**
 * GameEnvironment class.
 * @author gal
 */
public class GameEnvironment {
    // members
    private ArrayList<Collidable> objects;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.objects = new ArrayList<Collidable>();
    }

    /**
     * @return the Collidable list.
     */
    public ArrayList<Collidable> getEnvironment() {
        return this.objects;
    }

    /**
     * add the given collidable to the environment.
     * @param c
     *            collidable.
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     * @param trajectory
     *            the line we are looking for his intersection with one of the
     *            collidable objects
     * @return the collision point and the collidabe objec
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int n = this.objects.size();
        ArrayList<Integer> place = new ArrayList<Integer>();
        ArrayList<Point> coliding = new ArrayList<Point>();
        Rectangle rec;
        Collidable tmp;
        // we add all the collidable objects that intersect with the line into
        // ArrayList
        // and we keep there index in another list
        for (int i = 0; i < n; i++) {
            tmp = this.objects.get(i);
            rec = tmp.getCollisionRectangle();
            if (trajectory.closestIntersectionToStartOfLine(rec) != null) {
                coliding.add(trajectory.closestIntersectionToStartOfLine(rec));
                place.add(i);
            }
        }
        // if there are no coliding object that colide with the line - return
        // null
        if (coliding.isEmpty()) {
            return null;
        }
        Point start = trajectory.start();
        Point closest = coliding.get(0);
        int index = place.get(0);
        double distance = start.distance(coliding.get(0));
        // we look for the closest object that colide with the line and there
        // collision point
        for (int i = 0; i < coliding.size() - 1; i++) {
            if (distance > start.distance(coliding.get(i + 1))) {
                distance = start.distance(coliding.get(i + 1));
                index = place.get(i + 1);
                closest = coliding.get(i + 1);
            }
        }
        return new CollisionInfo(closest, this.objects.get(index));
    }

}