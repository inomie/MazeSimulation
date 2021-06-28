import java.util.ArrayList;
import java.util.Random;

public class LeftHandRuleRobot implements Robot{

    private Position pos;
    private Position prepos;
    private Maze maze;
    private int direction;
    private Position temp;

    /**
     * Constructor for LeftHandRuleRobot.
     * @param maze The maze map.
     */
    public LeftHandRuleRobot (Maze maze) {
        pos = maze.getStartPosition();
        prepos = null;
        this.maze = maze;
        direction = 3;
        //temp = getPos().getPosToEast();
    }

    /**
     * Moves the robot with the left hand on the wall all the time.
     */
    @Override
    public void move() {

        // 1 = North, 2 = West, 3 = South, 4 = East. To see the last step we did, in what direction it made.

        if(direction == 1){
            direction = 2;
        }
        else if(direction == 2){
            direction = 3;
        }
        else if(direction == 3){
            direction = 4;
        }
        else if(direction == 4){
            direction = 1;
        }

        do{

            //Set the left direction.
            Direction();

            //If it cant move then count down the direction variable.
            if(!maze.isMovable(temp)){
                direction--;
                if(direction < 1){
                    direction = 4;
                }
            }

        } while(!maze.isMovable(temp));

        //Set the new position and the previous position.
        prepos = pos;
        pos = temp;


    }

    /**
     * Checks the previous step to set the new left direction.
     */
    public void Direction() {

        if(direction == 1){
            temp = getPos().getPosToNorth();
        }
        else if(direction == 2){
            temp = getPos().getPosToWest();
        }
        else if(direction == 3){
            temp = getPos().getPosToSouth();
        }
        else if(direction == 4){
            temp = getPos().getPosToEast();
        }

    }

    /**
     * Get the position of the robot.
     * @return pos position of the robot.
     */
    @Override
    public Position getPos() {
        return pos;
    }

    /**
     * Set the position for the robot.
     * @param pos Position to set the robot to.
     */
    @Override
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Check if the robot have reached the goal.
     * @return true or false.
     */
    @Override
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
