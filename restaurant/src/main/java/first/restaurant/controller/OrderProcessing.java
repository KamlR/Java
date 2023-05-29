package first.restaurant.controller;

import first.restaurant.Order;
import first.restaurant.User;
import first.restaurant.UserData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

@RestController
public class OrderProcessing {

    // Предоставление информацию о меню (без количества оставшихся блюд) всем авторизованным пользователям.
    @GetMapping("menu")
    public String getMenu(@RequestParam(name = "email", defaultValue = "#") String email) throws SQLException {
        if(email.equals("#")){
            return "Incorrect data";
        }
        boolean check = false;
        for (User user : UserData.authorizedUsers) {
            if (user.getEmail().equals(email)) {
                check = true;
                break;
            }
        }
        if (!check) {
            return "You can see the menu because you are not logged in";
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM menu";
        ResultSet resultSet = statement.executeQuery(query);
        String menu = "";
        while (resultSet.next()) {
            menu += resultSet.getString("dish_name") + ": " + resultSet.getInt("price") + "; ";
        }
        return menu;
    }

    // Возможность сделать заказ всем авторизованным пользователям.
    @GetMapping("order")
    public String makeOrder(@RequestParam(name = "email", defaultValue = "#") String email,
                            @RequestParam(name = "dish", defaultValue = "#") String dish) throws SQLException {
        if(email.equals("#") || dish.equals("#")){
            return "Incorrect data";
        }
        boolean check = false;
        for (User user : UserData.authorizedUsers) {
            if (user.getEmail().equals(email)) {
                check = true;
                break;
            }
        }
        if (!check) {
            return "You can't make the order because you are not logged in";
        }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM menu";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getString("dish_name").equals(dish)) {
                if(resultSet.getInt("quantity") == 0){
                    return "This dish is not available";
                }
                int curr = resultSet.getInt("quantity") - 1;
                String sql = "UPDATE menu SET quantity = ? WHERE id = ?";
                PreparedStatement statementNew = connection.prepareStatement(sql);
                statementNew.setInt(1, curr);
                statementNew.setInt(2, resultSet.getInt("id"));
                statementNew.executeUpdate();
                UserData.orders.add(new Order(UserData.orderId, resultSet.getString("dish_name"), resultSet.getInt("price"), 0));
                UserData.orderId += 1;
                int id = UserData.orderId - 1;
                return "Your order info - ID: " + id + "; Name: " + resultSet.getString("dish_name") + "; Price:" + resultSet.getInt("price");
            }
        }
        return "There is no such dish in menu";
    }

    // Получение информации о статусе заказа.
    @GetMapping("orderInfo")
    public String getInfoOrder(@RequestParam(name = "email", defaultValue = "#") String email,
                               @RequestParam(name = "id", defaultValue = "#") String id) {
        if(email.equals("#") || id.equals("#")){
            return "Incorrect data";
        }
        boolean check = false;
        for (User user : UserData.authorizedUsers) {
            if (user.getEmail().equals(email)) {
                check = true;
                break;
            }
        }
        if (!check) {
            return "You can't see the order state because you are not logged in";
        }
        for (Order order : UserData.orders) {
            if (order.getId() == Integer.parseInt(id)) {
                order.setState(1);
                return "Your " + order.getDish_name() + " is ready";
            }
        }
        return "There is no order with such id";
    }
}
