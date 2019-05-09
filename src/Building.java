import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Building class that implements Sprite.
 * @author gal
 */
public class Building implements Sprite {
    // members
    private Point upperLeft;

    /**
     * constructor.
     */
    public Building() {
        this.upperLeft = new Point(50, 400);
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), 93, 200);
        d.setColor(Color.WHITE);
        int tmp = (int) this.upperLeft.getX() + 5;
        int tmpY = (int) this.upperLeft.getY() + 5;
        // creating the windows in the buildiung
        for (int i = 0; i < 5; i++) {
            int y = tmpY + (40 * i);
            for (int j = 0; j < 5; j++) {
                int x = tmp + (17 * j);
                d.fillRectangle(x, y, 15, 30);
            }
        }
        // creating the Antena
        d.setColor(Color.gray);
        d.fillRectangle(tmp + 24, (int) this.upperLeft.getY() - 60, 35, 60);
        d.fillRectangle(tmp + 34, (int) this.upperLeft.getY() - 250, 15, 250);
        Point center = new Point(tmp + 42, (int) this.upperLeft.getY() - 266);
        Circle c3 = new Circle(center, 16, Color.YELLOW, true);
        c3.drawOn(d);
        Circle c2 = new Circle(center, 9, Color.RED, true);
        c2.drawOn(d);
        Circle c1 = new Circle(center, 4, Color.white, true);
        c1.drawOn(d);

    }

    /**
     * @param dt
     *            is for the frames timing. notify the sprite that time has
     *            passed.
     */
    @Override
    public void timePassed(double dt) {
        // do nothing
    }

}
