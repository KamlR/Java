import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product {
    private int prod_type_id;
    private String prod_type_name;
    private boolean prod_is_food;
    private String prod_item_company;
    private String prod_item_unit;
    private int prod_item_quantity;
    public int getProd_type_id(){
        return prod_type_id;
    }

    public int getProd_item_quantity() {
        return prod_item_quantity;
    }
    public void changeProd_item_quantity(int we_need){
        prod_item_quantity -= we_need;
    }
}
