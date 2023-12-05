import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Frontend implements FrontendInterface{
<<<<<<< HEAD
=======

>>>>>>> frontend
    private final BackendInterface backend;
    private final Scanner scanner;

    public Frontend(BackendInterface backend, Scanner scanner) {
        this.backend = backend;
        this.scanner = scanner;
    }

    @Override
    public String startMainMenu() throws InputMismatchException {
        while (true) {
            System.out.println("Hello! Welcome to the UW Path Finder! What would you like to do?");
            System.out.println("1. Load in a graph file.");
            System.out.println("2. Display statistics of the current dataset.");
            System.out.println("3. Find the shortest path between two buildings.");
            System.out.println("4. Exit the application.");

            int userInput = Integer.parseInt(scanner.nextLine());
            switch (userInput) {
                case 1:
                    System.out.println("Please enter the filepath of your desired file.");
                    String filepath = scanner.nextLine();
                    try {
                        boolean output = loadUserFile(filepath);
                        if (output) {
                            System.out.println("File loaded successfully!");
                        } else {
                            System.out.println("Filetype is not right or file is corrupted.");
                            System.out.println("Please check your file or try another file.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                    } break;
                case 2:
                    System.out.println("Below is the current dataset.");
                    String result = displayCurrentDataset();
                    if (result == null) {
                        System.out.println("You have not loaded a file yet.");
                    } else {
                        System.out.println(result);
                    } break;
                case 3:
                    System.out.println("Please type in your starting building.");
                    String start = scanner.nextLine();
                    System.out.println("Please type in your destination building.");
                    String destination = scanner.nextLine();
                    String path = shortestPathBetween(start, destination);
                    System.out.println("The shortest path between " +start+ " and " +destination+" is:");
                    System.out.println(path);
                    break;
                case 4:
                    String farewell = exitApplication();
                    System.out.println(farewell);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input.");
            }
            if (returnMainMenu().equals("2")) {
                String farewell = exitApplication();
                System.out.println(farewell);
                System.exit(0);
            } else {
                System.out.println("Returning to main menu ...");
            }
        }
    }

    @Override
    public String returnMainMenu() throws InputMismatchException {
        System.out.println("Great! What else would you like to do?");
        System.out.println("1. Return to Main Menu.");
        System.out.println("2. Exit the application.");
        return scanner.nextLine();
    }

    @Override
    public boolean loadUserFile(String filePath) throws FileNotFoundException {
        return backend.readData(filePath);
    }

    @Override
    public String displayCurrentDataset() {
        return Arrays.toString(backend.getStatistics());
    }

    @Override
    public String shortestPathBetween(String origin, String destination) throws InputMismatchException {
        ShortestPathInterface shortest = backend.getShortestPath(origin, destination);
        List<String> paths = shortest.getPathSegments();
        List<Double> times = shortest.getWalkingTime();
        double totalTime = shortest.getTotalPathCost();
        return paths.toString() + " \n" + times.toString() + " \nand the total walking time is " + totalTime;
    }

    @Override
    public String exitApplication() {
        return "Thank you for using the UW Path finder. See you next time!";
    }
    
}
