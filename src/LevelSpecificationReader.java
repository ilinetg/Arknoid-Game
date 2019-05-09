import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSpecificationReader class.
 * @author gal
 */
public class LevelSpecificationReader {
    /**
     * @param reader
     *            Reader to the file we need to read from.
     * @return List of LevelInformation.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInfo = new ArrayList<LevelInformation>();
        LineNumberReader lineReader = new LineNumberReader(reader);
        String lineStr;
        List<String> information = new ArrayList<String>();
        try {
            lineStr = lineReader.readLine();
            while (lineStr != null) {
                // until we get to the START_LEVEL line
                while ((lineStr != null)
                        && (lineStr.compareTo("START_LEVEL") != 0)) {
                    lineStr = lineReader.readLine();
                }
                while ((lineStr != null)
                        && (lineStr.compareTo("END_LEVEL") != 0)) {
                    lineStr = lineReader.readLine();
                    information.add(lineStr);
                }
                if (!information.isEmpty()) {
                    levelInfo.add(this.splitStringList(information));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (levelInfo.isEmpty()) {
            System.err.println("not a good definition");
            System.exit(1);
        }
        try {
            lineReader.close();
        } catch (Exception e) {

        }
        return levelInfo;
    }

    /**
     * @param info
     *            arrayList of the level definitins information.
     * @return a levelInformation object.
     */
    private LevelInformation splitStringList(List<String> info) {
        String levelName = null;
        int[] paddle = new int[3];
        int speed = 0, width = 0, row = 0, startY = 0, startX = 0,
                numOfBlocks = 0;
        List<Double> blocksPosition = new ArrayList<Double>();
        List<Velocity> velocities = new ArrayList<Velocity>();
        List<Block> blocksOfLevel = new ArrayList<Block>();
        List<String> blocksOrder = new ArrayList<String>();
        String velocitiesOfBall = null;
        BlocksFromSymbolsFactory factory = null;
        String background1 = null, blockDifinition;
        Background background = null;
        int size = info.size();
        String str;
        int i = 0;
        int j = 0;
        while (!(info.isEmpty())) {
            str = info.get(i);
            String[] parts = str.split(":");
            int components = parts.length;
            if (components > 2) {
                System.err.println("not a good definition");
                System.exit(1);
            } else {
                switch (parts[0]) {
                case "level_name":
                    levelName = parts[1];
                    j++;
                    info.remove(i);
                    break;
                case "ball_velocities":
                    velocitiesOfBall = parts[1];
                    j++;
                    info.remove(i);
                    break;
                case "background":
                    Color color;
                    StringBuilder strokerTmp = new StringBuilder();
                    if (parts[1].contains("color")) {
                        strokerTmp.append(
                                parts[1].substring(parts[1].indexOf("("),
                                        parts[1].indexOf(")") + 1));
                        String stroker = strokerTmp.toString();
                        background1 = stroker;
                        if (stroker.contains("RGB")) {
                            int x = stroker.indexOf(")");
                            int y = stroker.indexOf("(");
                            StringBuilder temp = new StringBuilder();
                            temp.append(
                                    stroker.substring(stroker.indexOf("(") + 1,
                                            stroker.indexOf(")") + 1));
                            background1 = temp.toString();
                        }
                        background = new Background(BlockCreatorImplement
                                .colorFromString(background1));
                    } else {
                        strokerTmp.append(
                                parts[1].substring(parts[1].indexOf("(") + 1,
                                        parts[1].indexOf(")")));
                        background1 = strokerTmp.toString();
                        background = new Background(background1);
                    }
                    j++;
                    info.remove(i);
                    break;
                case "paddle_speed":
                    speed = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "paddle_width":
                    width = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "block_definitions":
                    blockDifinition = parts[1];
                    BufferedReader reader = null;
                    InputStream is = ClassLoader.getSystemClassLoader()
                            .getResourceAsStream(blockDifinition);
                    reader = new BufferedReader(new InputStreamReader(is));
                    factory = BlocksDefinitionReader.fromReader(reader);
                    j++;
                    info.remove(i);
                    break;
                case "blocks_start_x":
                    startX = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "blocks_start_y":
                    startY = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "row_height":
                    row = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "num_blocks":
                    numOfBlocks = Integer.parseInt(parts[1]);
                    j++;
                    info.remove(i);
                    break;
                case "START_BLOCKS":
                    j++;
                    while (!info.get(i).equals("END_BLOCKS")) {
                        blocksOrder.add(info.get(i));
                        info.remove(i);
                    }
                    info.remove(i);
                    blocksOrder.remove(0);
                    break;
                case "END_LEVEL":
                    j++;
                    info.remove(i);
                    break;
                default:
                    info.remove(i);
                    break;
                }
            }
        }
        if (j != 12) {
            System.err.println("bad definition  " + Integer.toString(j));
            System.exit(1);
        }
        blocksPosition.add((double) startX);
        blocksPosition.add((double) startY);
        blocksPosition.add((double) row);
        paddle[0] = speed;
        paddle[1] = width;
        paddle[2] = numOfBlocks;
        velocities = this.getVelocities(velocitiesOfBall);
        return new Level(levelName, background, paddle, velocities, factory,
                blocksOrder, blocksPosition);

    }

    /**
     * @param veloc
     *            string of numbers
     * @return List of velocities.
     */
    private List<Velocity> getVelocities(String veloc) {
        List<Velocity> velocities = new ArrayList<Velocity>();
        String[] parts = veloc.split(" ");
        int length = parts.length;
        for (int j = 0; j < length; j++) {
            String[] velocParm = parts[j].split(",");
            double angle = Integer.parseInt(velocParm[0]) + 0.0001;
            double speed = Integer.parseInt(velocParm[1]);
            velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
        return velocities;
    }
}
