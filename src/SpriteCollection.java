import java.util.ArrayList;

import biuoop.DrawSurface;

/**
 * SpriteCollection class.
 * @author gal
 */
public class SpriteCollection {
    // members
    private ArrayList<Sprite> sprites;

    /**
     * constructor- just initialize an arrayList of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * add the sprite to tha arrayList.
     * @param s
     *            - sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * @param i
     *            the index .
     * @return the sprite in the i place
     */
    public Sprite getSprite(int i) {
        return this.sprites.get(i);
    }

    /**
     * @return the size of the arrayList.
     */
    public int numOfSprite() {
        return this.sprites.size();
    }

    /**
     * call timePassed() on all sprites.
     * @param dt
     *            frame's timing parameter.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 1; i < (this.sprites.size() + 1); i++) {
            this.sprites.get(i - 1).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d
     *            DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        int n = this.sprites.size();
        for (int i = 0; i < n; i++) {
            this.sprites.get(i).drawOn(d);
            ;
        }
    }

    /**
     * @return the sprites list.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

}