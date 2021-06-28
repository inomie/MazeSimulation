import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import java.util.ArrayList;

public class MemoryRobot implements Robot {

    private Position pos;
    private Position prePos;
    private Maze maze;
    private Stack<Position> posStack;
    private Stack<Position> prePosStack;
    private HashMap<Position, Position> visitedHashMap;

    /**
     * Constructor for MemoryRobot.
     * @param maze The maze map.
     */
    public MemoryRobot (Maze maze) {
        pos = maze.getStartPosition();
        prePos = null;
        this.maze = maze;
        posStack = new Stack<Position>();
        prePosStack = new Stack<Position>();
        visitedHashMap = new HashMap<Position, Position>();
    }


    /**
     * Moves the robot in one direction and if it gets stuck it goes back and it saves the path in a stack.
     * The stack is going to have the way from start to the goal.
     * The hash map is going to have all the steps the robot made from start to goal.
     */
    @Override
    public void move() {
        Position temp = null;
        ArrayList<Position> Array = new ArrayList<>();

        /* Set in the four position around the robot in an array */
        Array.add(pos.getPosToEast());
        Array.add(pos.getPosToWest());
        Array.add(pos.getPosToNorth());
        Array.add(pos.getPosToSouth());

        while(true){

            //If the robot get in a position that the robot cant move it goes back.
            if(Array.isEmpty()){
                temp = posStack.pop();
                break;
            }

            //Get the first neighbour position and take it out of the list.
            temp = Array.get(0);
            Array.remove(temp);

            //If the new position is previous position reset the position.
            if(temp.equals(prePos)) {
                temp = null;
            }

            //Check if the robot can move to the position and check if its been there before.
            if((maze.isMovable(temp)) && (!visitedHashMap.containsKey(temp))){
                //Push the position to the stack and in to the hash map.
                posStack.push(pos);
                visitedHashMap.put(pos, pos);
                break;
            }

        }

        //Set new position and previous position.
        prePos = pos;
        pos = temp;

        //Check if position is the goal then push it in to the stack.
        if(maze.isGoal(pos)){
            posStack.push(pos);
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
