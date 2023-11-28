import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class FrontendDeveloperTests extends Frontend {	

    private static BackendInterface backend;
    private static FrontendInterface frontend;
    private static Scanner scanner;
    
    public FrontendDeveloperTests() {
	    super(new BackendPlaceholder(), new Scanner(System.in));
    }
    
    @Test
    public void testStartMainMenu() {
        TextUITester ui = new TextUITester("4");
        scanner = new Scanner(System.in);
        backend = new BackendPlaceholder();
        frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("See you next time!"))
            Assertions.fail();
    }

    @Test
    public void testReturnMainMenu() {
        TextUITester ui = new TextUITester("2");
        scanner = new Scanner(System.in);
        backend = new BackendPlaceholder();
        frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("What else would you like to do?"))
            Assertions.fail();
    }

    @Test
    public void testLoadUserFile() {
        TextUITester ui = new TextUITester("1\ncampus.dot\n1");
        scanner = new Scanner(System.in);
        backend = new BackendPlaceholder();
        frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("File loaded successfully!"))
            Assertions.fail();
    }

    @Test
    public void testLoadInvalidUserFile() {
        TextUITester ui = new TextUITester("1\ninvalid.dot\n1");
        scanner = new Scanner(System.in);
        backend = new BackendPlaceholder();
        frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("File does not exist."))
            Assertions.fail();
    }

    @Test
    public void testShortestPath() {
        TextUITester ui = new TextUITester("1\ncampus.dot\n1\n3");
        scanner = new Scanner(System.in);
        backend = new BackendPlaceholder();
        frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if(!result.contains("Please type in your starting building"))
            Assertions.fail();
    }
}
