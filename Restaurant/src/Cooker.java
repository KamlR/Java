import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cooker {
    private int cook_id;
    private String cook_name;
    public String getCook_name(){
        return cook_name;
    }
}
