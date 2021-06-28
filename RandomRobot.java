import java.util.ArrayList;
import java.util.Random;

public class RandomRobot implements Robot{
    private Position pos;
    private Position prepos;
    private Maze maze;
    private Random rand;

    /**
     * Constructor for RandomRobot.
     * @param maze The maze map.
     */
    public RandomRobot(Maze maze) {
        pos = maze.getStartPosition();
        prepos = null;
        this.maze = maze;
        rand = new Random();
    }

    /**
     * Gets a random position around the robot position.
     * If the robot can move to the random position then move the robot.
     */
    public void move() {
        Position temp = null;
        ArrayList<Position> Array = new ArrayList<>();

        /* Set in the four position around the robot in an array */
        Array.add(pos.getPosToEast());
        Array.add(pos.getPosToWest());
        Array.add(pos.getPosToNorth());
        Array.add(pos.getPosToSouth());

        do {
            /* Check if array is empty */
            if(Array.isEmpty()) {
                /* If array is empty go back tp preposition */
                temp = prepos;
                break;
            }

            /* Get a random position around the robot and then remove it from the array */
            temp = Array.get(rand.nextInt(Array.size()));
            Array.remove(temp);

            /* Check if ny position is the same as the preposition */
            if(temp.equals(prepos)) {
                temp = null;
            }

        } while(!maze.isMovable(temp));

        /* Set the new position and the new preposition */
        prepos = pos;
        pos = temp;
    }

    /**
     * Get the position of the robot.
     * @return pos position of the robot.
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Set the position for the robot.
     * @param pos Position to set the robot to.
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Check if the robot have reached the goal.
     * @return true or false.
     */
    public boolean ReachedGoal() {
        return maze.isGoal(pos);
    }

    /**
     * Gets the toString from maze and puts the robot on it's position in the maze.
     * Uses to print the maze.
     * @return string that the program can print.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(maze.toString());
        string.setCharAt((maze.getNumCulum()+1)*pos.getY()+pos.getX(), 'R');
        return string.toString();
    }
}
