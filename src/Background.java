import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * class Background implements Sprite- the game background.
 * @author gal
 */
public class Background implements Sprite {
    // members
    private SpriteCollection spriteObj;
    private Color color;
    private String img;

    /**
     * constructor.
     * @param col
     *            the background color.
     */
    public Background(Color col) {
        this.color = col;
        this.spriteObj = new SpriteCollection();
        this.img = null;
    }

    /**
     * constructor.
     * @param col
     *            the background color.
     * @param sprites
     *            collection of sprite added to the background.
     */
    public Background(Color col, SpriteCollection sprites) {
        this.color = col;
        this.spriteObj = sprites;
        this.img = null;
    }

    /**
     * constructor.
     * @param img
     *            the path to the image for the background.
     * @param col
     *            the background color.
     */
    public Background(String img, Color col) {
        this.color = col;
        this.spriteObj = new SpriteCollection();
        this.img = img;
    }

    /**
     * constructor.
     * @param img
     *            the path to the image for the background.
     */
    public Background(String img) {
        this.color = null;
        this.spriteObj = new SpriteCollection();
        this.img = img;
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        BufferedImage imgLog = null;
        // try to display the background
        try {
            InputStream is = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(this.img);
            imgLog = ImageIO.read(is);
            d.drawImage(0, 0, imgLog);

        } catch (Exception e) {
            // if it does not exist show the default color
            d.setColor(this.color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        }
        if (!this.spriteObj.getSprites().isEmpty()) {
            this.spriteObj.drawAllOn(d);
        }
        for (int i = 0; i < this.spriteObj.getSprites().size(); i++) {
            this.spriteObj.getSprites().get(i).drawOn(d);
        }
    }

    /**
     * notify the sprite that time has passed.
     * @param dt
     *            the frame timing parameter.
     */
    @Override
    public void timePassed(double dt) {
        // does nothing
    }

    /**
     * @return the list of sprites in the background.
     */
    public SpriteCollection getSprites() {
        return this.spriteObj;
    }
}
