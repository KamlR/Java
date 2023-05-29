package first.restaurant;

// Класс содержит информацию о заказе.
public class Order {
    private String dish_name;
    private int price;
    private int state;
    private int id;

    public Order(int id, String dish_name, int price, int state) {
        this.id = id;
        this.dish_name = dish_name;
        this.price = price;
        this.state = state;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
