import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    /**
     * Tests if the method "getX()" gives the correct value of X.
     */
    @Test
    void getX() {
        Position test = new Position(1,1);
        assertEquals(1, test.getX());
    }

    /**
     * Tests if the method "getY()" gives the correct value of Y.
     */
    @Test
    void getY() {
        Position test = new Position(1,1);
        assertEquals(1, test.getY());
    }

    /**
     * Test is checking if the method gives the correct position to the south of the robot.
     */
    @Test
    void getPosToSouth() {
        Position test = new Position(1,1);
        Position testpos = new Position(1,2);
        assertEquals(testpos, test.getPosToSouth());
    }

    /**
     * Test is checking if the method gives the correct position to the north of the robot.
     */
    @Test
    void getPosToNorth() {
        Position test = new Position(1,1);
        Position testpos = new Position(1,0);
        assertEquals(testpos, test.getPosToNorth());
    }

    /**
     * Test is checking if the method gives the correct position to the west of the robot.
     */
    @Test
    void getPosToWest() {
        Position test = new Position(1,1);
        Position testpos = new Position(0,1);
        assertEquals(testpos, test.getPosToWest());
    }

    /**
     * Test is checking if the method gives the correct position to the east of the robot.
     */
    @Test
    void getPosToEast() {
        Position test = new Position(1,1);
        Position testpos = new Position(2,1);
        assertEquals(testpos, test.getPosToEast());
    }

    /**
     * Tests if the method "equals()" gives the value "true" if two position is the same.
     */
    @Test
    void testEquals() {
        Position test = new Position(1,1);
        Position testpos = new Position(1,1);
        assertEquals(true, test.equals(testpos));
    }

    /**
     * Tests that the method "equals()" gives false if its not equals.
     */
    @Test
    void testNotEquals() {
        Position test = new Position(1,1);
        Position testpos = new Position(2,1);
        assertEquals(false, test.equals(testpos));
    }
}