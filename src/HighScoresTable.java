import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable class.
 * @author gal
 */
public class HighScoresTable {
    // members
    private ArrayList<ScoreInfo> scoreTable;
    private int maxLen;

    /**
     * sort the tble of highscores.
     */
    public void sort() {
        Collections.sort(this.scoreTable);
    }

    /**
     * constructor.Create an empty high-scores table with the specified size.
     * @param size
     *            means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.maxLen = size;
        this.scoreTable = new ArrayList<ScoreInfo>();
    }

    /**
     * Add a high-score.
     * @param score
     *            the scoreInfo we want to add to the table.
     */
    public void add(ScoreInfo score) {
        // if the score table is full
        if (this.scoreTable.size() > this.maxLen - 1) {
            this.sort();
            this.scoreTable.remove(this.size() - 1);
        }
        this.scoreTable.add(score);
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.scoreTable.size();
    }

    /**
     * The list is sorted such that the highest scores come first.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        this.sort();
        return this.scoreTable;
    }

    /**
     * @param score
     *            the cuurent score we want to get her rank.
     * @return the rank of the current score. Rank 1 means the score will be
     *         highest on the list. Rank `size` means the score will be lowest.
     *         Rank > `size` means the score is too low and will not be added to
     *         the list.
     */
    public int getRank(int score) {
        int rank = 1;
        for (ScoreInfo current : this.scoreTable) {
            if (score < current.getScore()) {
                rank++;
            } else {
                break;
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreTable.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename
     *            where the highScores are at.
     * @throws IOException
     *             if there was any problem reading from file
     */
    public void load(File filename) throws IOException {
        ObjectInputStream readenObject = null;
        try {
            readenObject = new ObjectInputStream(new FileInputStream(filename));
            for (int i = 0; i < this.maxLen; i++) {
                this.scoreTable.add((ScoreInfo) readenObject.readObject());
            }
        } catch (FileNotFoundException e) {
            // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) {
            System.err.println(
                    "Unable to find class for object in file: " + filename);
            return;
        } catch (Exception e) {
            return;
        } finally {
            try {
                if (readenObject != null) {
                    readenObject.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    //
    /**
     * Save table data to the specified file.
     * @param filename
     *            where we save the data.
     * @throws IOException
     *             if there was a problem with the file.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream writeToObject = null;
        try {
            writeToObject = new ObjectOutputStream(
                    new FileOutputStream(filename));
            for (int i = 0; i < this.scoreTable.size(); i++) {
                writeToObject.writeObject(this.scoreTable.get(i));
            }
        } catch (FileNotFoundException e) {
            // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } finally {
            try {
                if (writeToObject != null) {
                    writeToObject.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or
     * there is a problem with reading it, an empty table is returned.
     * @param filename
     *            the file we read from.
     * @return Scores table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        ObjectInputStream readenObject = null;
        ArrayList<ScoreInfo> table = new ArrayList<ScoreInfo>();
        HighScoresTable highScores = new HighScoresTable(5);
        try {
            readenObject = new ObjectInputStream(new FileInputStream(filename));
            for (int i = 0; i < 5; i++) {
                highScores.add((ScoreInfo) readenObject.readObject());
            }
            return highScores;
        } catch (FileNotFoundException e) {
            // Can't find file to open
            highScores = new HighScoresTable(table.size());
            return highScores;
        } catch (ClassNotFoundException e) {
            System.err.println(
                    "Unable to find class for object in file: " + filename);
            highScores = new HighScoresTable(table.size());
            return highScores;
        } catch (IOException e) {
            // Some other problem
            return highScores;
        } finally {
            try {
                if (readenObject != null) {
                    readenObject.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}
