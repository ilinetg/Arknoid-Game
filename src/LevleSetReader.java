import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * LevleSetReader class.
 * @author gal
 */
public class LevleSetReader {
    /**
     * @param path
     *            to the file of the levels sets
     * @return map with the levels key and discription.
     */
    public static Map<String, String> setReader(String path) {
        InputStreamReader read = new InputStreamReader(
                ClassLoader.getSystemClassLoader().getResourceAsStream(path));
        BufferedReader inl = new BufferedReader(read);

        Map<String, String> deficulties = new TreeMap<String, String>();

        LineNumberReader lineReader = new LineNumberReader(read);
        String[] data = new String[2];
        String lineStr = null;
        try {
            lineStr = lineReader.readLine();
            while (lineStr != null) {
                // if it an odd line
                if ((lineReader.getLineNumber() % 2) == 1) {
                    data[0] = lineStr;
                } else {
                    data[1] = lineStr;
                    deficulties.put(data[0], data[1]);

                    data = new String[2];
                }
                lineStr = lineReader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return deficulties;
    }

}
