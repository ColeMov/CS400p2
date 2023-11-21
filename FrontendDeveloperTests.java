import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class FrontendDeveloperTests {
    @Test
    public void testStartMainMenu() {
        TextUITester ui = new TextUITester("4");
        Scanner scanner = new Scanner(System.in);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.equals("See you next time!"))
            Assertions.fail();
    }

    @Test
    public void testReturnMainMenu() {
        TextUITester ui = new TextUITester("2");
        Scanner scanner = new Scanner(System.in);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("What else would you like to do?"))
            Assertions.fail();
    }

    @Test
    public void testLoadUserFile() {
        TextUITester ui = new TextUITester("1\ncampus.dot\n1");
        Scanner scanner = new Scanner(System.in);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("File loaded successfully!"))
            Assertions.fail();
    }

    @Test
    public void testLoadInvalidUserFile() {
        TextUITester ui = new TextUITester("1\ninvalid.dot\n1");
        Scanner scanner = new Scanner(System.in);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if (!result.contains("File does not exist."))
            Assertions.fail();
    }

    @Test
    public void testShortestPath() {
        TextUITester ui = new TextUITester("1\ncampus.dot\n1\n3");
        Scanner scanner = new Scanner(System.in);
        BackendInterface backend = new BackendPlaceholder();
        FrontendInterface frontend = new Frontend(backend, scanner);
        frontend.startMainMenu();
        String result = ui.checkOutput();
        if(!result.contains("Please type in your starting building"))
            Assertions.fail();
    }
}
