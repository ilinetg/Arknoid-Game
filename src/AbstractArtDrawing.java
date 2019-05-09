import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * AbstractArtDrawing class.
 * @author gal
 */
public class AbstractArtDrawing {
    /**
     * this method draw 10 lines and their midle point in blue and their
     * intersection points with each other in red.
     */
    public void drawLines() {
        // Create a window with the title "Random Lines Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        int r = 3;
        Line[] lines = new Line[10];
        // creat an array of 10 random lines
        lines = this.generateRandomLine();
        for (int i = 0; i < 10; ++i) {
            int x1 = (int) lines[i].start().getX();
            int y1 = (int) lines[i].start().getY();
            int x2 = (int) lines[i].end().getX();
            int y2 = (int) lines[i].end().getY();
            Point midle = lines[i].middle();
            double dx = midle.getX();
            double dy = midle.getY();
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);
            d.setColor(Color.BLUE);
            d.fillCircle((int) dx, (int) dy, r);
        }
        // find the intersection points of every line with the other lines in
        // the array and draw them in red color.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    Point intersection = lines[i].intersectionWith(lines[j]);
                    int x = (int) intersection.getX();
                    int y = (int) intersection.getY();
                    d.setColor(Color.RED);
                    d.fillCircle(x, y, r);
                }
            }
        }
        gui.show(d);
    }

    /**
     * this method creats an array of 10 random lines.
     * @return array with 10 lines.
     */
    public Line[] generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            Line l1 = new Line(x1, y1, x2, y2);
            lines[i] = l1;
        }
        return lines;
    }

    /**
     * main method.
     * @param args
     *            no input.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();

        example.drawLines();
    }
}
