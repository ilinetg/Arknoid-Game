/**
 * BlockCreator Interface.
 * @author gal
 */
public interface BlockCreator {

    /**
     * @param xpos
     *            x cordinate of the block upper left point
     * @param ypos
     *            y cordinate of the block upper left point
     * @return Create a block at the specified location.
     */
    Block create(int xpos, int ypos);
}
