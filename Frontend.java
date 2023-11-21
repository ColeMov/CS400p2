import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Frontend implements FrontendInterface{

    public Frontend(BackendInterface backend, Scanner scanner) {
    }

    @Override
    public String startMainMenu() throws InputMismatchException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startMainMenu'");
    }

    @Override
    public String returnMainMenu() throws InputMismatchException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnMainMenu'");
    }

    @Override
    public void loadUserFile(String filePath) throws FileNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserFile'");
    }

    @Override
    public String displayCurrentDataset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayCurrentDataset'");
    }

    @Override
    public String shortestPathBetween(String origin, String destination) throws InputMismatchException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortestPathBetween'");
    }

    @Override
    public String exitApplication() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exitApplication'");
    }
    
}
