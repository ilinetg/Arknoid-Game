import java.awt.Color;

/**
 * SpacerBlock class implements BlockCreator. is a block with no height.
 * @author gal
 */
public class SpacerBlock implements BlockCreator {
    // members
    private int width;

    /**
     * constructor.
     * @param wid
     *            the spacer width.
     */
    public SpacerBlock(int wid) {
        this.width = wid;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block;
        Point upperLeft = new Point(xpos, ypos);

        block = new Block(upperLeft, this.width, 0, Color.WHITE);
        return block;
    }

    /**
     * @return spacer's width.
     */
    public int getWidth() {
        return this.width;
    }
}