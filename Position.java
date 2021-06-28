import java.util.Objects;

public class Position {

    private int x;
    private int y;

    /**
     * The position for the robot in the maze in x and y axis.
     * @param x position in x-axis
     * @param y position in y-axis
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x axis position.
     * @return x axis position.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y axis position.
     * @return y axis position.
     */
    public int getY() {
        return y;
    }

    /**
     * Get the south position from the robot position.
     * @return south position.
     */
    public Position getPosToSouth(){
        return new Position(x, y + 1);
    }

    /**
     * Get the north position from the robot position.
     * @return north position.
     */
    public Position getPosToNorth(){
        return new Position(x, y - 1);
    }

    /**
     * Get the west position from the robot position.
     * @return west position.
     */
    public Position getPosToWest(){
        return new Position(x - 1, y);
    }

    /**
     * Get the east position from the robot position.
     * @return east position.
     */
    public Position getPosToEast(){
        return new Position(x + 1, y);
    }

    /**
     * Check if two positions are equals.
     * @param o an object
     * @return true or false.
     */
    public boolean equals(Object o){
        if(o instanceof Position){
            Position temp = (Position) o;
            return x == temp.getX() && y == temp.getY();
        }
        return false;
    }

    /**
     * Make a hashcode from position x-axis and y-axis.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
