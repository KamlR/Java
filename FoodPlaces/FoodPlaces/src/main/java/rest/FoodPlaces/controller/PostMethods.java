package rest.FoodPlaces.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.FoodPlaces.clients.Order;

import java.sql.*;

@RestController
public class PostMethods {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlacesForFood", "postgres", "LsqlF");
    ;
    Statement statement;
    PreparedStatement preparedStatement;
    String query;
    ResultSet resultSet;

    public PostMethods() throws SQLException {
    }

    public boolean checkDish(int restId, int dishId) throws SQLException {
        if (restId == 1) {
            query = "SELECT * FROM RabbitMenu WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dishId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } else if (restId == 2) {
            query = "SELECT * FROM GoldenMenu WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dishId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        }
        return false;
    }

    public void reduceDishCount(int restId, int dishId) throws SQLException {
        String sql;
        if (restId == 1) {
            sql = "UPDATE RabbitMenu SET count = ? WHERE id = ?";
        } else {
            sql = "UPDATE GoldenMenu SET count = ? WHERE id = ?";
        }
        int currQuantity = resultSet.getInt("count") - 1;
        PreparedStatement statementNew = connection.prepareStatement(sql);
        statementNew.setInt(1, currQuantity);
        statementNew.setInt(2, dishId);
        statementNew.executeUpdate();
    }

    @PostMapping("orders")
    public String makeOrder(@RequestBody Order order) throws SQLException {
        query = "SELECT * FROM restaurants WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, order.getRestId());
        resultSet = preparedStatement.executeQuery();
        String restaurantName;
        String orderClient;
        while (resultSet.next()) {
            restaurantName = resultSet.getString("name");
            if (!checkDish(order.getRestId(), order.getDishId())) {
                return "There is no dish with such id in " + restaurantName;
            }
            if (order.getCount() > resultSet.getInt("count")) {
                return "There is not enough amount of this dish now in" + restaurantName;
            }
            orderClient = "Your order is: " + resultSet.getString("name") + ", " + resultSet.getInt("price");
            reduceDishCount(order.getRestId(), order.getDishId());
            return orderClient;
        }
        return "There is no restaurant with such id";
    }
}
