package first.restaurant;

import java.util.ArrayList;

public class UserData {
    // Список авторизованных пользователей.
    public static ArrayList<User> authorizedUsers = new ArrayList<>();
    // Список заказов.
    public static ArrayList<Order> orders = new ArrayList<>();
    // ID заказа. При добавлении нового в соотв. методе, значение увеличивается на 1.
    public static int orderId = 1;
}
