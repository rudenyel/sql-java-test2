package cz.murkaliza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;

public class ExampleJDBC {

    public static void main(String... args) {
        try {

            // Simple JDBC lifecycle
            Connection connection = DriverManager.getConnection("jdbc:sqlite:diary.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM books");
            while (resultSet.next()) {
                System.out.println("Total books: " + resultSet.getInt(1));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
