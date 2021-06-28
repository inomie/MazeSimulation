import java.io.*;
import java.util.ArrayList;

public class Maze {

    private ArrayList<ArrayList<Character>> MazeArray;
    private Position startPos;

    /**
     * Reads the input file of the maze to an array.
     * @param stream inputfile.
     * @throws IOException
     */
    public Maze(Reader stream) throws IOException {
        MazeArray = new ArrayList<>();

        BufferedReader reader = new BufferedReader(stream);

        String line;
        int yPos = 0;
        int Starts = 0, Goals = 0;
        /* Reads one line at the time of the maze */
        while((line = reader.readLine()) != null) {
            MazeArray.add(new ArrayList<Character>());
            /* Reads the char for each position */
            for(int xPos = 0; xPos < line.length(); xPos++) {
                MazeArray.get(yPos).add(line.charAt(xPos));
                /* Check if it's a start position and keep count of how many start the maze have */
                if(line.charAt(xPos) == 'S') {
                    startPos = new Position(xPos, yPos);
                    Starts++;
                }
                /* Keeping count of how many goals there is in the maze */
                else if(line.charAt(xPos) == 'G') {
                    Goals++;
                }
            }
            yPos++;
        }
        /* Can only be one start in the maze */
        if(Starts != 1) {
            throw new IllegalStateException("No start or to many");
        }
        /* Must be more at least one goal in the maze */
        else if(Goals < 1) {
            throw new IllegalStateException("No goals");
        }
    }

    /**
     * Check if robot can move from the position.
     * @param p Position of the robot
     * @return true or false.
     */
    public boolean isMovable(Position p) {
        if (p == null) {
            return false;
        }
        else if (p.getX() < 0 || p.getY() < 0) {
            return false;
        }
        else if (p.getY() > MazeArray.size() || p.getX() > MazeArray.get(p.getY()).size()) {
            return false;
        }
        return MazeArray.get(p.getY()).get(p.getX()) != '*';
    }

    /**
     * Check if the position is the goal.
     * @param p Position of the robot
     * @return true or false.
     */
    public boolean isGoal(Position p) {
        return MazeArray.get(p.getY()).get(p.getX()) == 'G';
    }

    /**
     * Get the start position for the robot.
     * @return start position.
     */
    public Position getStartPosition() {
        return startPos;
    }

    /**
     * Check how many rows the array have.
     * @return number of rows.
     */
    public int getNumRows() {
        return MazeArray.size();
    }

    /**
     * Check how many columns the array have.
     * @return numbers of columns.
     */
    public int getNumCulum() {
        if(!MazeArray.isEmpty()) {
            return MazeArray.get(0).size();
        }
        return 0;
    }

    /**
     * Make's a string of the maze to print it out.
     * @return string.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < MazeArray.size(); i++) {
            for(int j = 0; j < MazeArray.get(i).size(); j++) {
                string.append(MazeArray.get(i).get(j));
            }
            string.append('\n');
        }
        return string.toString();
    }
}
