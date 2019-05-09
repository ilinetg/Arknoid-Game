import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * BlockCreatorImplement class that implements BlockCreator interface.
 * @author gal
 */
public class BlockCreatorImplement implements BlockCreator {
    // members
    private Color outside;
    private double width;
    private double height;
    private int counter;
    private ArrayList<String> fillStrList;

    /**
     * constructor.
     * @param creatorStrs
     *            the information about the block values
     * @param filler
     *            the fill color/image of the block in each hit points situation
     */
    public BlockCreatorImplement(Map<String, String> creatorStrs,
            Map<Integer, String> filler) {
        this.height = Double.parseDouble(creatorStrs.get("height"));
        this.width = Double.parseDouble(creatorStrs.get("width"));
        this.outside = BlockCreatorImplement
                .colorFromString(creatorStrs.get("stroke"));
        this.counter = Integer.parseInt(creatorStrs.get("hit_points"));
        this.fillStrList = new ArrayList<String>();
        for (int i = 0; i < this.counter + 1; i++) {
            if (filler.containsKey(i)) {
                this.fillStrList.add(Integer.toString(i));
                this.fillStrList.add(filler.get(i));
            }
        }
    }

    // Create a block at the specified location.
    @Override
    public Block create(int xpos, int ypos) {
        Point upperLeft = new Point(xpos, ypos);
        Block block;
        Map<Integer, BufferedImage> imgMap = new TreeMap<Integer, BufferedImage>();
        Map<Integer, Color> colorMap = new TreeMap<Integer, Color>();
        int i = 0;

        while (i < this.fillStrList.size()) {

            // Check if this string is an image
            if (fillStrList.get(i + 1).contains("image")) {
                try {
                    InputStream is = ClassLoader.getSystemClassLoader()
                            .getResourceAsStream(this.fillStrList.get(i + 1));
                    // Add the image to an array
                    imgMap.put(Integer.valueOf(this.fillStrList.get(i)),
                            ImageIO.read(is));
                } catch (Exception e) {
                    imgMap = null;
                    break;
                }
            } else {
                // it's a color

                Color blockColor = BlockCreatorImplement
                        .colorFromString(fillStrList.get(i + 1));
                if (blockColor != null) {
                    colorMap.put(Integer.parseInt(fillStrList.get(i)),
                            blockColor);
                }
            }
            i += 2;
            ;
        }

        // Create the block
        block = new Block(upperLeft, this.width, this.height, colorMap, imgMap,
                this.outside, this.counter);

        return block;
    }

    /**
     * @param str
     *            a string with the color.
     * @return a Color object
     */
    public static Color colorFromString(String str) {
        if (str == null) {
            return null;
        }
        Color color = null;
        String[] ints;
        int r = 0, g = 0, b = 0;
        if (str.contains(",")) {
            // str starts with RGB - we get rid of it
            str = str.substring(4, str.indexOf(")"));
            ints = str.split(",");
            r = Integer.parseInt(ints[0]);
            g = Integer.parseInt(ints[1]);
            b = Integer.parseInt(ints[2]);
            color = new Color(r, g, b);
            return color;
        } else {
            if (str.contains("(")) {
                str = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
            }
            switch (str.toUpperCase()) {
            case "BLACK":
                return Color.BLACK;
            case "BLUE":
                return Color.BLUE;
            case "GRAY":
                return Color.GRAY;
            case "GREEN":
                return Color.GREEN;
            case "PINK":
                return Color.PINK;
            case "RED":
                return Color.RED;
            case "ORANGE":
                return Color.ORANGE;
            case "CYAN":
                return Color.CYAN;
            case "YELLOW":
                return Color.YELLOW;
            case "WHITE":
                return Color.WHITE;
            case "MAGENTA":
                return Color.MAGENTA;
            case "DARK_GRAY":
                return Color.DARK_GRAY;
            case "DARKGRAY":
                return Color.DARK_GRAY;
            case "LIGHT_GRAY":
                return Color.LIGHT_GRAY;
            case "LIGHTGRAY":
                return Color.LIGHT_GRAY;
            default:
                // if str doesnt match any of the cases return null.
                return null;

            }

        }
    }
}
