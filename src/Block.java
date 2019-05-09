import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;

/**
 * Block class- implements Collidable ,Sprite and HitNotifier.
 * @author gal
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // members
    private Rectangle block;
    private Color blockColor;
    private int hitNum;
    private List<HitListener> hitListeners;

    private Map<Integer, java.awt.Color> colorMap = null;
    private Map<Integer, BufferedImage> imgMap = null;

    /**
     * constructor.
     * @param upperLeft
     *            the block's shape left upper point.
     * @param width
     *            the block's shape's width
     * @param height
     *            the block's shape's height
     */
    public Block(Point upperLeft, double width, int height) {
        this.block = new Rectangle(upperLeft, width, height);
        this.blockColor = Color.GRAY;
        this.hitNum = 1;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor.
     * @param upperLeft
     *            the block's shape left upper point.
     * @param width
     *            the block's shape's width
     * @param height
     *            the block's shape's height
     * @param color
     *            the block's color
     */
    public Block(Point upperLeft, double width, int height, Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.blockColor = color;
        this.hitNum = 1;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor.
     * @param upperLeft
     *            the upper left block point
     * @param width
     *            the block width
     * @param height
     *            the block height
     * @param colorMap
     *            a map of the inside color of the block for which hit points
     * @param imgMap
     *            a map of the inside image of the block for which hit points
     * @param stroke
     *            the block border color
     * @param hitPoints
     *            the block initial hit points
     */
    public Block(Point upperLeft, double width, double height,
            Map<Integer, Color> colorMap, Map<Integer, BufferedImage> imgMap,
            Color stroke, int hitPoints) {
        this.block = new Rectangle(upperLeft, width, height);
        this.hitNum = hitPoints;
        this.blockColor = stroke;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorMap = colorMap;
        this.imgMap = imgMap;
    }

    @Override
    /**
     * @return the block's shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    /**
     * @param collisionPoint
     *            the point where the collision occures
     * @param currentVelocity
     *            given velocity of the current coliding object
     * @return the new velocity expected after the hit (based on the force the
     *         object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        ArrayList<Line> blockLines = this.block.rectEdges();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if we hit the edge points
        if (this.block.getUpperLeft() == collisionPoint
                || this.block.getUpperRight() == collisionPoint
                || this.block.getLowerLeft() == collisionPoint
                || this.block.getLowerRight() == collisionPoint) {
            this.hitNum--;
            this.notifyHit(hitter);
            return new Velocity(dx * (-1), dy * (-1));
        }
        // if we hit the horizontal edges
        if ((collisionPoint.getY() == blockLines.get(0).start().getY())
                || (collisionPoint.getY() == blockLines.get(1).start()
                        .getY())) {
            this.hitNum--;
            this.notifyHit(hitter);
            return new Velocity(dx, dy * (-1));
        }
        // if we hit the vertical edges
        if ((collisionPoint.getX() == blockLines.get(2).start().getX())
                || (collisionPoint.getX() == blockLines.get(3).start()
                        .getX())) {
            this.hitNum--;
            this.notifyHit(hitter);
            return new Velocity(dx * (-1), dy);
        } else {
            return currentVelocity;
        }
    }

    @Override
    /**
     * draw the sprite to the screen.
     * @param d
     *            DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.block.getUpperLeft().getX();
        int y = (int) this.block.getUpperLeft().getY();
        int width = (int) this.block.getWidth();
        int height = (int) this.block.getHeight();

        // In case the image exists for the current key (hit points), draw it.
        if ((this.imgMap != null) && (this.imgMap.containsKey(this.hitNum))) {
            d.drawImage(x, y, this.imgMap.get(this.hitNum));
            // Otherwise, if the color follows those requirements, draw it.
        } else if ((this.colorMap != null)
                && (this.colorMap.containsKey(this.hitNum))) {
            d.setColor(this.colorMap.get(this.hitNum));
            d.fillRectangle(x, y, width, height);
        } else {
            // If both doesn't work show the default color.
            d.setColor(Color.WHITE); // default color
            d.fillRectangle(x, y, width, height);
        }
        // if there is a stroke color
        if (this.blockColor != null) {
            d.setColor(this.blockColor);
            d.drawRectangle(x, y, width, height);
        }
    }

    @Override
    /**
     * notify the sprite that time has passed.- here it does nothing
     */
    public void timePassed(double dt) {

    }

    /**
     * add this object to the game.
     * @param gameLevel
     *            its the Game.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * @return the block's color.
     */
    public Color getColor() {
        return this.blockColor;
    }

    /**
     * sets the block's color.
     * @param color
     *            the color we want to give the block.
     */
    public void setColor(Color color) {
        this.blockColor = color;
    }

    /**
     * @return block's hit number.
     */
    public int getHitNum() {
        return this.hitNum;
    }

    /**
     * sets the block hit number.
     * @param x
     *            blocks hit number to be given
     */
    public void setHitNum(int x) {
        this.hitNum = x;
    }

    /**
     * removes the block from the game.
     * @param gameLevel
     *            the level we remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @return list of block's listeners.
     */
    public List<HitListener> getListenera() {
        return this.hitListeners;
    }

    /**
     * @param hitter
     *            the hitting object.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if ((this.hitListeners == null) || (this.hitListeners.isEmpty())) {
            return;
        }
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

}
