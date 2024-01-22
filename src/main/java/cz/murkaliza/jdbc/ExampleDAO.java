package cz.murkaliza.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import java.sql.ResultSet;


public class ExampleDAO {

    public static void main(String... args) {
        try {

            // DAO lifecycle
            Connection connection = DriverManager.getConnection("jdbc:sqlite:diary.db");
            BookDAO books = new BookDAO(connection);

            // Create book
            Book book = new Book();
            book.setTitle("new title");
            book.setFirstName("new first name");
            book.setLastName("new last name");
            book = books.create(book);
            System.out.println("Create book: " + book);
            long new_book_id = book.getId();

            // Find book by id
            Book new_book = books.findById(new_book_id);
            System.out.println("Find book: " + new_book);
            // and change title
            new_book.setTitle("change title");
            new_book = books.update(new_book);
            System.out.println("Change title: " + new_book);
            // and then delete
            books.delete(new_book.getId());

            // Print all books
            books.findAll().forEach(System.out::println);

            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
