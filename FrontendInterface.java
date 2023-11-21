import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public interface FrontendInterface{
    /* Constructor
     * Instantiate a backend object
     * Instantiate a java.util.scanner
     * */

    /**
     * This method will start the user interactive loop
     * <p>
     *     Welcome to the UW Path Finder! What would you like to do?
     *     1. Load in a graph file.
     *     2. Display statistics of the current dataset.
     *     3. Find the shortest path between two buildings.
     *     4. Exit the application.
     * </p>
     *
     * */
    public String startMainMenu() throws InputMismatchException;

    /**
     * This method will be displayed after every interaction
     * with the user except after exit the application.
     * <p>
     *     Great! What else would you like to do?
     *     1. Return to Main Menu.
     *     2. Exit the application.
     * </p>
     * */
    public String returnMainMenu() throws InputMismatchException;

    /**
     * This method will load in the file provided by the user.
     * If the file does not exist, it throws a FileNotFoundException.
     * */
    public void loadUserFile(String filePath) throws FileNotFoundException;

    /**
     * This method will display the current dataset's statistics including
     * 1. Number of buildings (nodes)
     * 2. Number of edges
     * 3. Total walking time (sum of all edge weights)
     * */
    public String displayCurrentDataset();

    /**
     * This method will ask the user for two buildings (start and destination)
     * and return the shortest path between them, including all buildings on the way,
     * the walking time for each segment, and the total estimated walking time.
     * */
    public String shortestPathBetween(String origin, String destination) throws InputMismatchException;

    /**
     * This method will be called once the user choose the exit option at the
     * main menu, and it will end the program and print out a farewell message.
     * */
    public String exitApplication();
}
