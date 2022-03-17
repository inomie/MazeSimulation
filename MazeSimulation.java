import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Graphical simulator for a robot in a maze.
 * @author Robin Söderberg
 * @date 2022-03-14
 */
public class MazeSimulation {

    public enum RobotType {Random,Memory,LeftHand}
    public static final int MAX_STEPS=100;
    private DisplayPanel[][] mazeDisplay;
    private Maze maze;
    private JTextField messageField;
    private JPanel mazePanel;

    /**
     * @param args Arguments to the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeSimulation::new);
    }

    /**
     * Create th window
     */
    public MazeSimulation() {

        JFrame theWindow = setupMainWindow();
        JMenuBar menuBar = createMenuBar();
        theWindow.setJMenuBar(menuBar);

        mazePanel = new JPanel();
        theWindow.add(mazePanel,BorderLayout.CENTER);
        messageField = new JTextField();
        theWindow.add(messageField,BorderLayout.SOUTH);

        JPanel topPanel = createTopPanel();
        theWindow.add(topPanel,BorderLayout.NORTH);
        theWindow.setVisible(true);
        loadMaze();
    }


    /**
     * Function will create the menubar for the application.
     * @return the configured menubar.
     */
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newFile = new JMenuItem("New file");
        newFile.addActionListener(e->this.loadMaze());
        JMenuItem Exit = new JMenuItem("Exit");
        Exit.addActionListener(e->System.exit(0));
        menu.add(newFile);
        menu.add(Exit);
        menuBar.add(menu);
        return menuBar;
    }

    /**
     * function will create buttons so the user can select what
     * type of robot it wants to use.
     * @return panel that contains all the buttons.
     */
    protected JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,0));

        /* Buttons */
        JButton leftHandButton = new JButton("LeftHand Robot");
        topPanel.add(leftHandButton);
        JButton memoryButton = new JButton("Memory Robot");
        topPanel.add(memoryButton);
        JButton randomButton = new JButton("Random Robot");
        topPanel.add(randomButton);

        /* Button to clear the field */
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e->setupMazePanel());
        topPanel.add(clearButton);

        /* Button actions */
        ActionListener randomRobot=new RobotButtonListener(RobotType.Random);
        randomButton.addActionListener(randomRobot);
        ActionListener memRobot=new RobotButtonListener(RobotType.Memory);
        memoryButton.addActionListener(memRobot);
        ActionListener leftRobot=new RobotButtonListener(RobotType.LeftHand);
        leftHandButton.addActionListener(leftRobot);

        return topPanel;
    }

    /**
     * Set colors.
     */
    public static class DisplayPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        public void setWall() {
            this.setBackground(Color.BLACK);
        }

        public void setEmpty() {
            this.setBackground(Color.WHITE);
        }

        public void setRobot() {
            this.setBackground(Color.RED);
        }

        public void setGoal() {
            this.setBackground(Color.GREEN);
        }

    }

    /**
     * Function will ask the user for a maze file.
     */
    protected void loadMaze() {
        File file = null;
        boolean done = false;

        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Maze file", "maze");
        fileChooser.setFileFilter(filter);

        do {
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                /* If the user are not giving any file */
                System.exit(0);
            }
            try {
                /* Reading the the maze map */
                maze = (new Maze(new FileReader(file)));
                mazeDisplay = new DisplayPanel[maze.getNumRows()][maze.getNumCulum()];
                mazePanel.setLayout(new GridLayout(maze.getNumRows(), maze.getNumCulum()));
                done=true;
            } catch (FileNotFoundException e) {
                updateMessage("Unable to open maze file");
            } catch (IOException e) {
                updateMessage("Unable to read maze file");
            }
            catch (Exception e) {
                updateMessage("Something wrong with map");
            }
        } while(!done);

        setupMazePanel();
    }

    /**
     * Create the window
     * @return the window
     */
    protected JFrame setupMainWindow() {
        JFrame theWindow;
        theWindow = new JFrame("Maze");
        theWindow.setSize(1000, 1000);
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return theWindow;
    }

    /**
     * Print the maze in the window.
     */
    private void setupMazePanel() {
        /* remove all the components if new map is loaded */
        mazePanel.removeAll();
        for(int i = 0; i < maze.getNumRows(); i++) {
            for(int j = 0; j < maze.getNumCulum(); j++) {
                DisplayPanel displayPanel = new DisplayPanel();
                if(!maze.isMovable(new Position(j, i))) {
                    displayPanel.setWall();
                } else if(maze.isGoal(new Position(j, i))) {
                    displayPanel.setGoal();
                } else {
                    displayPanel.setEmpty();
                }

                mazePanel.add(displayPanel);
                mazeDisplay[i][j] = displayPanel;
            }
        }
        mazePanel.doLayout();
    }

    /**
     * Robot moving in the maze
     * @param robot the specific robot in the maze.
     */
    public void runSimulation(Robot robot) {
        /* Start position */
        Position robotPos = robot.getPos();
        /* Print robot in the maze */
        displayRobot(robotPos);
        int i = 0;

        do {
            /* remove the robot from the maze print */
            removeRobot(robot.getPos());
            robot.move();
            /* Print the robot in the new position */
            displayRobot(robot.getPos());
            /* Make the thread sleep for 500ms to simulate steps*/
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            i++;
        } while(!robot.ReachedGoal() && i < MAX_STEPS);

        if(robot.ReachedGoal())
            updateMessage("Robot reached the goal after " + i + " steps");
        else
            updateMessage("Robot did not reach the goal before using the maximum number of steps");
    }

    /**
     * Print out message to the user.
     * @param string the message.
     */
    private void updateMessage(final String string) {
        SwingUtilities.invokeLater(()->messageField.setText(string));
    }

    /**
     * Remove the robot from the position in the maze.
     * @param pos the position were the robot is.
     */
    private void removeRobot(final Position pos) {
        SwingUtilities.invokeLater(()->mazeDisplay[pos.getY()][pos.getX()].setEmpty());
    }

    /**
     * Print the robot in the specific position.
     * @param robotPos the position were the robot is going to be printed.
     */
    private void displayRobot(final Position robotPos) {
        SwingUtilities.invokeLater(()-> mazeDisplay[robotPos.getY()][robotPos.getX()].setRobot());
    }

    class RobotButtonListener implements ActionListener {

        private MazeSimulation.RobotType type;

        /**
         * @param robot what type of robot
         */
        public RobotButtonListener(MazeSimulation.RobotType robot) {
            type = robot;
        }


        @Override
        public void actionPerformed(ActionEvent event) {
            /* Use multi thread so the program will not crash if the user
            *  starts multi robots at once */
            new Thread(() -> {
                try {
                    switch (type) {
                        case Memory -> runSimulation(new MemoryRobot(maze));
                        case LeftHand -> runSimulation(new LeftHandRuleRobot(maze));
                        case Random -> runSimulation(new RandomRobot(maze));
                    }
                } catch(Exception e) {
                    updateMessage("Simulation failed because of "+ e.getClass().getName());
                }

            }).start();
        }
    }
}
