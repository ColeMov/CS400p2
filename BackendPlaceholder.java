import java.io.FileNotFoundException;

public class BackendPlaceholder implements BackendInterface {

    @Override
    public boolean readData(String filepath) throws FileNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readData'");
    }

    @Override
    public ShortestPathInterface getShortestPath(String origin, String destination) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShortestPath'");
    }

    @Override
    public Double[] getStatistics(ShortestPathInterface path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatistics'");
    }
    
}
