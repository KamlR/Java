package first.restaurant.controller;

import first.restaurant.User;
import first.restaurant.UserData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;


@RestController
public class UsersFirstSteps {

    // Регистрация пользовталей.
    // В базу данных добавляется имя, почта, пароль, роль.
    @GetMapping("registration")
    public String registration(@RequestParam(name = "name", defaultValue = "#") String name,
                               @RequestParam(name = "email", defaultValue = "#") String email,
                               @RequestParam(name = "pass", defaultValue = "#") String pass,
                               @RequestParam(name = "role", defaultValue = "#") String role) throws SQLException {
        if(name.equals("#") || email.equals("#") || pass.equals("#")){
            return "Incorrect data";
        }
        if(!role.equals("9012")){
            role = "client";
        }
        else{
            role = "manager";
        }
        String query;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        if (!email.contains("@")) {
            return "Your email is not correct!";
        }
        query = "SELECT COUNT(*) FROM users WHERE email = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, email);
        resultSet = statement.executeQuery();
        if (resultSet.next() && resultSet.getInt(1) > 0) {
            return "User with such email already exists";
        }
        if (pass.length() < 4) {
            return "Your password length is too short";
        }
        query = "insert into users (name, email, password, role) values (?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, pass);
        statement.setString(4, role);
        statement.executeUpdate();
        return "The user has been successfully registered";
    }


    // Предоставление информации о пользователе по его id.
    @GetMapping("{idStr}")
    public String getUserInfo(@PathVariable String idStr) throws SQLException {
        int id = Integer.parseInt(idStr);
        String query;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        query = "SELECT * FROM users WHERE id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        String info = "";
        while (resultSet.next()) {
            info = "Id: " + resultSet.getInt("id") + "; Name: " + resultSet.getString("name");
        }
        if (info.equals("")) {
            return "There is no user with such id";
        }
        return info;
    }

    // Авторизация пользователя для возможности взаимодействия в рестораном.
    @GetMapping("authorization")
    public String authorization(@RequestParam(name = "email", defaultValue = "#") String email,
                                @RequestParam(name = "pass", defaultValue = "#") String pass) throws SQLException {
        if(email.equals("#") || pass.equals("#")){
            return "Incorrect data";
        }
        String query;
        PreparedStatement statement;
        ResultSet resultSet;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "LsqlF");
        query = "SELECT * FROM users WHERE email=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, email);
        resultSet = statement.executeQuery();
        boolean info = false;
        while (resultSet.next()) {
            info = true;
            if (pass.equals(resultSet.getString("password"))) {
                UserData.authorizedUsers.add(new User(email, pass, resultSet.getString("role")));
            } else {
                return "Your password is not correct";
            }
        }
        if (!info) {
            return "You are not registered in the system";
        }
        return "You have successfully logged in";
    }
}
