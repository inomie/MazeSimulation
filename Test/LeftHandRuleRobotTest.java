import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LeftHandRuleRobotTest {

    private Maze maze;

    /**
     * Take one step in move() for leftHandRuleRobot to see if its moving in correct direction.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveOneStep() throws IOException {
        maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        LeftHandRuleRobot test = new LeftHandRuleRobot(maze);
        Position testpos = test.getPos().getPosToEast();
        test.move();
        assertEquals( testpos, test.getPos());
    }

    /**
     * Moves to a corner to see if its going one step back or not.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void moveToCorner() throws IOException {
        maze = new Maze(new StringReader("*****\n*S  *\n**G**\n*****"));
        LeftHandRuleRobot test = new LeftHandRuleRobot(maze);
        test.move();
        Position testpos = test.getPos();
        test.move();
        test.move();
        assertEquals( testpos, test.getPos());
    }

    /**
     * See if the method getPos() gives the correct position of the robot.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void getPos() throws IOException {
        maze = new Maze(new StringReader("*****\n*S  *\n***G*"));
        LeftHandRuleRobot test = new LeftHandRuleRobot(maze);
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
        LeftHandRuleRobot test = new LeftHandRuleRobot(maze);
        Position testPos = test.getPos();
        test.setPos(test.getPos().getPosToEast());
        assertEquals(testPos.getPosToEast(), test.getPos());
    }

    /**
     * Test if the steps the robot should take to the goal is correct.
     * @throws IOException If it fails to read string(Will not happen in this case).
     */
    @Test
    void reachedGoal() throws IOException {
        maze = new Maze(new StringReader("***S***\n*     *\n* *** *\n*G*****"));
        LeftHandRuleRobot test = new LeftHandRuleRobot(maze);
        for(int i = 0; i < 11; i++){
            test.move();
        }

        assertEquals(true, test.ReachedGoal());
    }
}