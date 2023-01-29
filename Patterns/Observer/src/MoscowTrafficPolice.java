import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoscowTrafficPolice extends TrafficPolice {
    public MoscowTrafficPolice() {
        posts = new ArrayList<String>(Arrays.asList("Москва-1", "Москва-2", "Москва-3"));
    }

}
