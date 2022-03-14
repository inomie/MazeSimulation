import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Author: Robin Lundin Söderberg
 * Date: 2021-04-12
 *
 * Robot that takes a random path till it finds the goal.
 */
public class RobotTest {

    /**
     * Reads the input file and make maze.
     * Move the robot until it found a goal.
     * If it's no way to the goal(s) in the maze it's loops forever.
     * @param args The maze file.
     */
    public static void main(String[] args) {
        Maze maze = null;

        try {
            /* Reads the input file and makes the maze of it */
            maze = new Maze(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            /* Error if no file is found as an input */
            System.err.println("No file found");
            System.exit(1);
        } catch (IOException e) {
            /* Error can open or read the input file */
            System.err.println("File error");
            System.exit(1);
        }
        MemoryRobot robot = new MemoryRobot(maze);

        /* The program, does till it reaches an goal */
        do {
            robot.move();
            System.out.println(robot);
        } while(!robot.ReachedGoal());

        System.out.println("Has reached goal");
    }
}

