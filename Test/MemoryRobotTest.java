import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class MemoryRobotTest {

    private Maze maze;

    /*
     ***S***
     *     *
     * *** *
     *G*****
     */

    /**
     * Test if the method "move()" is going in the correct direction.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveOneStep() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        MemoryRobot test = new MemoryRobot(maze);
        Position testPos = test.getPos().getPosToSouth();
        test.move();
        assertEquals(testPos, test.getPos());
    }

    /**
     * Tests if the robot is going to move to east first if it can even if it can go in another direction.
     * @throws IOException  If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveToEastFirst() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        MemoryRobot test = new MemoryRobot(maze);
        test.move();
        Position testPos = test.getPos().getPosToEast();
        test.move();
        assertEquals(testPos, test.getPos());
    }

    /**
     * Test if the robot is going the right way to the goal and take right amount of steps.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveToGoal() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        MemoryRobot test = new MemoryRobot(maze);
        int step = 0;
        while(!maze.isGoal(test.getPos())){
            test.move();
            step++;
        }
        assertEquals(11, step);
    }

    /**
     * Tests if the method "getPos()" gives the correct position of the robot.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getPos() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        MemoryRobot test = new MemoryRobot(maze);
        Position testPos = maze.getStartPosition();
        assertEquals(testPos, test.getPos());
    }

    /**
     * Tests if the method "setPos()" is setting the robot to the right position.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void setPos() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        MemoryRobot test = new MemoryRobot(maze);
        Position testPos = test.getPos();
        test.setPos(test.getPos().getPosToEast());
        assertEquals(testPos.getPosToEast(), test.getPos());
    }

    /*
     ***S***
     *     *
     * *** *
     *****G*
     */

    /**
     * Tests if the method "reachedGoal()" is saying that the robot have reached the goal.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void reachedGoal() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*****G*"));
        MemoryRobot test = new MemoryRobot(maze);
        test.move();
        test.move();
        test.move();
        test.move();
        test.move();
        assertEquals(true, test.ReachedGoal());
    }
}