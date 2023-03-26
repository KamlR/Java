import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Operation {
    private int oper_type_id;
    private String oper_type_name;
    private double oper_type_time;
    public int getOper_type_id(){
        return oper_type_id;
    }
    public double getOper_type_time(){
        return  oper_type_time;
    }
}
