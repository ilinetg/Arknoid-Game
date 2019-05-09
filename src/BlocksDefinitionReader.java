import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BlocksDefinitionReader- reads from a file the definitions of spacers and
 * block in the level.
 * @author gal
 */
public class BlocksDefinitionReader {
    /**
     * @param reader
     *            the file we read from.
     * @return a BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        String lineStr = null;
        BlocksFromSymbolsFactory blockFactory = null;
        TreeMap<String, SpacerBlock> spacers = new TreeMap<String, SpacerBlock>();
        Map<String, String> defaults = new TreeMap<String, String>();
        TreeMap<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        BufferedReader inputReader = new BufferedReader(reader);
        try {
            lineStr = inputReader.readLine();
            // we read line after line until we get to the end of file
            while (lineStr != null) {
                String[] part = lineStr.split(" ");
                // ignoring the lines which doesnt start with default|bdef|sdef
                // or empty lines
                while ((part[0].compareTo("default") != 0)
                        && ((part[0].compareTo("bdef") != 0))
                        && ((part[0].compareTo("sdef") != 0))
                        && (part != null)) {
                    lineStr = inputReader.readLine();
                    if (lineStr != null) {
                        part = lineStr.split(" ");
                    }
                }
                // if we got to the lines we dont ignore
                switch (part[0]) {
                case "default":
                    int n = part.length;
                    for (int i = 1; i < n; i++) {
                        String[] tmp = part[i].split(":");
                        defaults.put(tmp[0], tmp[1]);
                    }
                    break;
                case "bdef":
                    BlockCreator created = BlocksDefinitionReader.getBlock(part,
                            defaults);
                    int i = 0;
                    String[] s = part[0].split(":");
                    while (!(s[0].equals("symbol"))) {
                        i++;
                        s = part[i].split(":");
                    }
                    blockCreators.put(s[1], created);
                    break;
                case "sdef":
                    int j = 1;
                    String[] tmp = part[1].split(":");
                    while (!(tmp[0].equals("symbol"))) {
                        j++;
                        tmp = part[j].split(":");
                    }
                    SpacerBlock space = BlocksDefinitionReader.getSpacer(part);
                    spacers.put(tmp[1], space);
                    break;
                default:
                    break;
                }
                lineStr = inputReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("problem with block definition file");
        }
        blockFactory = new BlocksFromSymbolsFactory(spacers, blockCreators);
        try {
            inputReader.close();
        } catch (Exception e) {
            System.out.println("some problem closing file");
        }
        return blockFactory;
    }

    /**
     * @param line
     *            array of spacer information.
     * @return a spacerBlock.
     */
    public static SpacerBlock getSpacer(String[] line) {
        SpacerBlock block;
        int i = 0;
        String[] tmp = line[0].split(":");
        while (!(tmp[0].equals("width"))) {
            i++;
            tmp = line[i].split(":");
        }
        int wid = Integer.parseInt(tmp[1]);
        block = new SpacerBlock(wid);
        return block;
    }

    /**
     * @param line
     *            an array with blockCreator information.
     * @param defualtVal
     *            the default values which run over the blockCreator values
     * @return a BlockCreator object.
     */
    public static BlockCreator getBlock(String[] line,
            Map<String, String> defualtVal) {
        String background1 = null;
        Map<String, String> values = new TreeMap<String, String>();
        Map<Integer, String> fillMap = new TreeMap<Integer, String>();
        int n = line.length;
        String patternString = "fill[-]?";
        Pattern pattern = Pattern.compile(patternString);

        for (int i = 1; i < n; i++) {
            String[] part = line[i].split(":");
            Matcher matcher = pattern.matcher(part[0]);
            switch (part[0]) {
            case "width":
                if (defualtVal.containsKey("width")) {
                    values.put(part[0], defualtVal.get("width"));
                    break;
                }
                values.put(part[0], part[1]);
                break;
            case "height":
                if (defualtVal.containsKey("height")) {
                    values.put(part[0], defualtVal.get("height"));
                    break;
                }
                values.put(part[0], part[1]);
                break;
            case "hit_points":
                if (defualtVal.containsKey("hit_points")) {
                    values.put(part[0], defualtVal.get("hit_points"));
                    break;
                }
                values.put(part[0], part[1]);
                break;
            case "stroke":
                StringBuilder strokerTmp = new StringBuilder();
                strokerTmp.append(part[1].substring(part[1].indexOf("("),
                        part[1].indexOf(")")));
                String stroker = strokerTmp.toString();
                if (stroker.contains("RGB")) {
                    StringBuilder temp = new StringBuilder();
                    temp.append(stroker.substring(stroker.indexOf("("),
                            stroker.indexOf(")")));
                    values.put(part[0], temp.toString());
                } else {
                    values.put(part[0], stroker);
                }
                break;
            default:
                break;
            }
            // if we have some data that starts with "fill"
            if (matcher.find()) {
                int hit;
                StringBuilder fill = new StringBuilder();
                try {
                    int y = part[0].indexOf("-");
                    hit = Integer.parseInt(part[0].substring(
                            part[0].indexOf("-") + 1, part[0].length()));
                } catch (Exception e) {
                    hit = 1;
                }

                StringBuilder strokerTmp = new StringBuilder();
                if (part[1].contains("color")) {
                    strokerTmp.append(part[1].substring(part[1].indexOf("("),
                            part[1].indexOf(")") + 1));
                    String stroker = strokerTmp.toString();
                    background1 = stroker;
                    if (stroker.contains("RGB")) {
                        int x = stroker.indexOf(")");
                        int y = stroker.indexOf("(");
                        StringBuilder temp = new StringBuilder();
                        temp.append(stroker.substring(stroker.indexOf("(") + 1,
                                stroker.indexOf(")") + 1));
                        background1 = temp.toString();
                    }
                } else {
                    strokerTmp.append(part[1].substring(
                            part[1].indexOf("(") + 1, part[1].indexOf(")")));
                    background1 = strokerTmp.toString();
                    fillMap.put(hit, background1);
                }
                fillMap.put(hit, background1);
            }
        }
        // run over the values we already have in default
        String[] checkList = new String[4];
        checkList[0] = "height";
        checkList[1] = "width";
        checkList[2] = "hit_points";
        checkList[3] = "stroke";
        for (int k = 0; k < 4; k++) {
            // go over the blockCreator values(only from the checkList)- if one
            // of them exist in the default and doesnt exist in the block give
            // the block this value.
            if (!values.containsKey(checkList[k])) {
                if (defualtVal.containsKey(checkList[k])) {
                    values.put(checkList[k], defualtVal.get(checkList[k]));
                } else {
                    // we dont change the stroke value
                    if (checkList[k].equals("stroke")) {
                        continue;
                    }
                }
            }
        }
        // check we have all values we need
        int count = values.keySet().size();
        if ((count != 4) && (count != 3)) {
            System.err.println("not a good definition");
            System.exit(1);
        }
        if (fillMap.isEmpty()) {
            System.err.println("not a good definition");
            System.exit(1);
        }
        BlockCreator blockCreated = new BlockCreatorImplement(values, fillMap);
        return blockCreated;
    }
}
