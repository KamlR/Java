import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VisitorOrder {
    private String vis_name;
    private ArrayList<Integer> vis_ord_dishes;

    public String getVis_name() {
        return vis_name;
    }

    public ArrayList<Integer> getVis_ord_dishes() {
        return vis_ord_dishes;
    }
}
