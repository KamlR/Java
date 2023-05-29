package first.restaurant.controller;

import first.restaurant.User;
import first.restaurant.UserData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

// Класс работает предназанчен для работы с менеджерами.
@RestController
public class Manager {

    // Получение всего меню, включая кол-во каждого блюда.
    @GetMapping("countDishes")
    public String getInfoDishes(@RequestParam(name = "email", defaultValue = "#") String email) throws SQLException {
        if (email.equals("#")){
            return "Incorrect data";
        }
        for (User user : UserData.authorizedUsers) {
            if (user.getEmail().equals(email) && user.getRole().equals("manager")) {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM menu";
                ResultSet resultSet = statement.executeQuery(query);
                String menu = "";
                while (resultSet.next()) {
                    menu += resultSet.getString("dish_name") + ": " + resultSet.getInt("price") + ", " + resultSet.getInt("quantity") + "; ";
                }
                return menu;
            } else if (user.getEmail().equals(email) && user.getRole().equals("client")) {
                return "You don't have rights to view this section";
            }
        }
        return "You are not logged in";
    }

    // Возможность обновить кол-во имеющегося блюда.
    @GetMapping("add")
    public String addDish(@RequestParam(name = "email", defaultValue = "#") String email,
                          @RequestParam (name = "id", defaultValue = "#") String id,
                          @RequestParam (name = "quantity", defaultValue = "#") String quantity) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        if (email.equals("#") || id.equals("#") || quantity.equals("#")){
            return "Incorrect data";
        }
        for (User user : UserData.authorizedUsers) {
            if (user.getEmail().equals(email) && user.getRole().equals("manager")) {
                String sql = "UPDATE menu SET quantity = ? WHERE id = ?";
                if(!checkId(Integer.parseInt(id))){
                    return "There is no dish with such id";
                }
                PreparedStatement statementNew = connection.prepareStatement(sql);
                statementNew.setInt(1, Integer.parseInt(quantity));
                statementNew.setInt(2, Integer.parseInt(id));
                statementNew.executeUpdate();
                return "Quantity with dish_id" + id + " has updated";
            } else if (user.getEmail().equals(email) && user.getRole().equals("client")) {
                return "You don't have rights to view this section";
            }
        }
        return "You are not logged in";
    }

    // Проверка существования id блюда.
    public boolean checkId(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM menu";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if(resultSet.getInt("id") == id){
                return true;
            }
        }
        return false;
    }
}
