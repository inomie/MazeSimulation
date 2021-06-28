import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    /**
     *  Test if it can read in a string of the maze.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void readFile() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
    }

    /**
     * Check if the robot can move too the position.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void isMovable() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        Position test = maze.getStartPosition();
        assertTrue(maze.isMovable(test));
    }

    /**
     *  A test where the method "isMovable()" try a position where it is not movable.
     *  If the test passes then the method discovers that the position is not movable.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void isMovableWrong() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        Position test = new Position(0,0);
        assertFalse(maze.isMovable(test));
    }

    /**
     * Test if the method "isGoal()" can see if the robot is on the goal.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void isGoal() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        Position test = new Position(3,2);
        assertTrue(maze.isGoal(test));
    }

    /**
     * The test try to see if the method "isGoal()" says that its not a goal.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void isGoalWrong() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        Position test = new Position(2,2);
        assertFalse(maze.isGoal(test));
    }

    /**
     * Test is checking if the method "getStartPosition()" is giving the correct start position.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getStartPosition() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        Position test = new Position(1,1);
        assertEquals(test, maze.getStartPosition());
    }

    /**
     * Test is checking if the method "getNumRows()" gives the correct number of rows in the maze.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getNumRows() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        assertEquals(3, maze.getNumRows());
    }

    /**
     * Test is checking if the method "getNumCulum()" gives the correct number of columns in the maze.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getNumCulum() throws IOException {
        Maze maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        assertEquals(5, maze.getNumCulum());
    }
}