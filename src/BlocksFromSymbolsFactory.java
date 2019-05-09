import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksFromSymbolsFactory class that holds tow maps.
 * @author gal
 */
public class BlocksFromSymbolsFactory {
    // members
    private Map<String, SpacerBlock> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     * @param spaces
     *            map for creating spacerBlocks.
     * @param blocks
     *            map for creating BlockCreator.
     */
    public BlocksFromSymbolsFactory(TreeMap<String, SpacerBlock> spaces,
            TreeMap<String, BlockCreator> blocks) {
        this.spacerWidths = spaces;
        this.blockCreators = blocks;
    }

    /**
     * @param s
     *            the space symbol.
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return (this.spacerWidths.containsKey(s));
    }

    /**
     * @param s
     *            the block symbol.
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return (this.blockCreators.containsKey(s));
    }

    /**
     * @param s
     *            the symbol.
     * @param xpos
     *            cordinate x of block.
     * @param ypos
     *            cordinate y of block.
     * @return a block according to the definitions associated with symbol s
     */
    public Block getBlock(String s, int xpos, int ypos) {
        if (this.isBlockSymbol(s)) {
            return this.blockCreators.get(s).create(xpos, ypos);
        } else if (this.isSpaceSymbol(s)) {
            return this.spacerWidths.get(s).create(xpos, ypos);
        } else {
            return null;
        }
    }

    /**
     * @param s
     *            spacer symbol.
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s).getWidth();
    }
}