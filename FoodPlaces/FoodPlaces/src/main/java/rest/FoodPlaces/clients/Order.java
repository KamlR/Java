package rest.FoodPlaces.clients;

public class Order {
    private int restId;
    private int dishId;
    private int count;

    public Order(int restId, int dishId, int count) {
        this.restId = restId;
        this.dishId = dishId;
        this.count = count;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
