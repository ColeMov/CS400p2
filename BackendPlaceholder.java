import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class BackendPlaceholder implements BackendInterface {
    @Override
    public boolean readData(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return false;
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException();
        }
    }
    @Override
    public ShortestPathInterface getShortestPath(String origin, String destination) {
        return new ShortestPathInterface () {
            @Override
            public List<String> getPathSegments() {
                return new ArrayList<>();
            }

            @Override
            public List<Double> getWalkingTime() {
                return new ArrayList<>();
            }

            @Override
            public double getTotalPathCost() {
                return 0.0;
            }
        };
    }

    @Override
    public Double[] getStatistics() {
        return new Double[]{1.4,2.2,3.1,4.9};
    }
}
