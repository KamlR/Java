import java.util.ArrayList;
import java.util.Arrays;

public class NovgorodTrafficPolice extends TrafficPolice {
    public NovgorodTrafficPolice() {
        posts = new ArrayList<String>(Arrays.asList("Новгород-1", "Новгород-2", "Новогород-3"));
    }
}
