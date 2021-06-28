import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomRobotTest {

    private Maze maze;

    /**
     * Tests if the robot is moving to random position if it can.
     * Counts how many times the two robots get the same position. If its the same position ten times in a row it fails.
     * It can happen that the robots get the same position some times but if they get the same ones 10 in a row
     * the chances that the robot is random is really small then.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveRandom() throws IOException {
        maze = new Maze(new StringReader("*******\n*     *\n*  S  *\n*     *\n*G*****"));
        int count = 0;
        for(int i = 0; i < 10; i++){
            RandomRobot test = new RandomRobot(maze);
            test.move();
            RandomRobot test1 = new RandomRobot(maze);
            test1.move();
            if(test.equals(test1)){
                count += 1;
            }
        }
        assertNotEquals(10, count);
    }

    /**
     * See if the method "getPos()" gives the correct position of the robot.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getPos() throws IOException {
        maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        RandomRobot test = new RandomRobot(maze);
        Position testPos = maze.getStartPosition();
        assertEquals(testPos, test.getPos());
    }

    /**
     * See if the method setPos() sets the correct position of the robot.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void setPos() throws IOException {
        maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        RandomRobot test = new RandomRobot(maze);
        Position testPos = test.getPos();
        test.setPos(test.getPos().getPosToEast());
        assertEquals(testPos.getPosToEast(), test.getPos());
    }

    /**
     * Tests if the robot has reached the goal.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void reachedGoal() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        RandomRobot test = new RandomRobot(maze);
        Position testPos = new Position(1,3);
        test.setPos(testPos);
        assertEquals(true, maze.isGoal(test.getPos()));
    }
}