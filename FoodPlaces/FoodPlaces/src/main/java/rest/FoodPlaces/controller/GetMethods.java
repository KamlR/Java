package rest.FoodPlaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

@RestController
public class GetMethods {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    String query;
    ResultSet resultSet;

    @GetMapping("restaurants")
    public String getAllRestaurants() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlacesForFood", "postgres", "LsqlF");
        statement = connection.createStatement();
        query = "SELECT * FROM restaurants";
        resultSet = statement.executeQuery(query);
        String restaurants = "";
        while (resultSet.next()) {
            restaurants += resultSet.getString("name") + ", " + resultSet.getString("address") + ", " + resultSet.getFloat("rating") + "; ";
        }
        return restaurants;
    }

    public void getTableMenu(int id) throws SQLException {
        if (id == 1) {
            statement = connection.createStatement();
            query = "SELECT * FROM RabbitMenu";
            resultSet = statement.executeQuery(query);
        } else if (id == 2) {
            statement = connection.createStatement();
            query = "SELECT * FROM GoldenMenu";
            resultSet = statement.executeQuery(query);
        }
    }

    @GetMapping("menu/{idStr}")
    public String getRestaurantMenu(@PathVariable String idStr) throws SQLException {
        int id = Integer.parseInt(idStr);
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PlacesForFood", "postgres", "LsqlF");
        query = "SELECT * FROM restaurants WHERE id=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String menu = "";
        while (resultSet.next()) {
            getTableMenu(id);
            while (resultSet.next()) {
                menu += resultSet.getString("name") + ", " + resultSet.getString("description") + ", " + resultSet.getString("price") + "; ";
            }
        }
        if (menu.equals("")) {
            return "There is no restaurant with such id";
        }
        return menu;
    }
}
