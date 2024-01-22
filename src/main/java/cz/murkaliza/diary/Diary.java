package cz.murkaliza.diary;

import java.sql.*;

import cz.murkaliza.jdbc.Book;
import cz.murkaliza.jdbc.BookDAO;

import cz.murkaliza.menu.TextMenuItem;
import cz.murkaliza.menu.TextMenu;

import java.util.Scanner;

public class Diary {

    private static String dbName = "diary.db";
    private static Connection dbConnection;

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS books (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title VARCHAR(127) CHECK(title != '')," +
            "first_name VARCHAR(127) CHECK(first_name != '')," +
            "last_name VARCHAR(127) CHECK(last_name != '')," +
            "year INTEGER DEFAULT 0" +
            ")";

    private static final String CHECK_BOOK_TABLE =
            "SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'books'";

    private static final TextMenuItem listAsIs = new TextMenuItem("As is", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            books.findAll().forEach(System.out::println);
        }
    });

    private static final TextMenuItem listSortedByAuthor = new TextMenuItem("Sorted by author", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            books.findAllOrderByAuthor().forEach(System.out::println);
        }
    });

    private static final TextMenuItem listSortedByTitle = new TextMenuItem("Sorted by title", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            books.findAllOrderByTitle().forEach(System.out::println);
        }
    });

    private static final TextMenuItem findByAuthor = new TextMenuItem("By author", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Last name > ");
            String author = scanner.nextLine();
            books.findByAuthor(author).forEach(System.out::println);
        }
    });

    private static final TextMenuItem findByTitle = new TextMenuItem("By title", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Title > ");
            String title = scanner.nextLine();
            books.findByTitle(title).forEach(System.out::println);
        }
    });

    private static final TextMenuItem addBook = new TextMenuItem("Add", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            Scanner scanner = new Scanner(System.in);

            Book book = new Book();
            System.out.print("Title > ");
            book.setTitle(scanner.nextLine());
            System.out.print("First name > ");
            book.setFirstName(scanner.nextLine());
            System.out.print("Last name > ");
            book.setLastName(scanner.nextLine());
            System.out.print("Year > ");
            book.setYear(Integer.parseInt(scanner.nextLine()));

            books.create(book);
        }
    });

    private static final TextMenuItem updateBook = new TextMenuItem("Update", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID > ");
            long id = Long.parseLong(scanner.nextLine());

            Book book = books.findById(id);
            System.out.print("Title > ");
            String title = scanner.nextLine();
            if (!title.trim().isEmpty()) { book.setTitle(title); }
            System.out.print("First name > ");
            String first_name = scanner.nextLine();
            if (!first_name.trim().isEmpty()) { book.setFirstName(first_name); }
            System.out.print("Last name > ");
            String last_name = scanner.nextLine();
            if (!last_name.trim().isEmpty()) { book.setLastName(last_name); }
            System.out.print("Year > ");
            String year = scanner.nextLine();
            if (!year.trim().isEmpty()) { book.setYear(Integer.parseInt(year)); }

            books.update(book);
        }
    });

    private static final TextMenuItem deleteBook = new TextMenuItem("Delete", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID > ");
            long id = Long.parseLong(scanner.nextLine());

            Book book = books.findById(id);
            books.delete(book.getId());
        }
    });

    private static final TextMenuItem olderBook = new TextMenuItem("Older book", new Runnable() {
        public void run() {
            BookDAO books = new BookDAO(dbConnection);
            books.findOlderBook().forEach(System.out::println);
        }
    });
    private static final TextMenu subMenuList = new TextMenu(
            "List >", true, false,
            listAsIs, listSortedByAuthor, listSortedByTitle);
    private static final TextMenu subMenuFind = new TextMenu(
            "Find >", true, false,
            findByAuthor, findByTitle);
    private static final TextMenu topMenu = new TextMenu(
            "Current database " + dbName, false, true,
            subMenuList, subMenuFind, addBook, updateBook, deleteBook, olderBook);

    public static void main(String... args) {
        try {
            System.out.println("Create (or open if exists) database:");
            System.out.print("Database filename (default diary.db) > ");
            Scanner scanner = new Scanner(System.in);
            String filename = scanner.nextLine();
            if (!filename.trim().isEmpty()) { dbName = filename; }

            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(CHECK_BOOK_TABLE);
            if (!resultSet.isBeforeFirst()) {
                statement.executeUpdate(CREATE_TABLE);
            }

            topMenu.run();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
